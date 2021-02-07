package com.example.fynddemoproject.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fynddemoproject.R
import com.example.fynddemoproject.base.BaseFragment
import com.example.fynddemoproject.databinding.FragmentListBinding
import com.example.fynddemoproject.detail.DetailActivity
import com.example.fynddemoproject.network.remote.RetrofitService
import com.example.fynddemoproject.util.AppConstants
import com.example.fynddemoproject.util.AppEnums
import javax.inject.Inject

class ListFragment : BaseFragment(), ListAdapter.ItemClickListener {

    var movieListType: AppEnums.MovieListType? = null
    val viewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
            HomeViewModel::class.java
        )
    }

    @Inject
    lateinit var retrofitService: RetrofitService

    lateinit var binding: FragmentListBinding
    lateinit var listAdapter: ListAdapter

    companion object {

        const val SPAN_COUNT = 2

        fun newInstance(movieLostType: AppEnums.MovieListType): ListFragment {
            val listFragment = ListFragment()
            Bundle().apply {
                this.putSerializable(AppConstants.NavigationExtras.EXTRA_MOVIE_TYPE, movieLostType)
                listFragment.arguments = this
            }
            return listFragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieListType =
                it.getSerializable(AppConstants.NavigationExtras.EXTRA_MOVIE_TYPE) as AppEnums.MovieListType?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun initializeObservers() {
        super.initializeObservers()
        viewModel.pagedListLiveData?.observe(viewLifecycleOwner, {
            hideProgressBar()
            listAdapter.submitList(it)
        })
    }

    override fun initializeViews() {
        binding.listMovies.layoutManager = GridLayoutManager(requireActivity(), SPAN_COUNT)
        listAdapter = ListAdapter(this)
        binding.listMovies.adapter = listAdapter
        movieListType?.let {
            showProgressBar()
            viewModel.setMovieType(it, retrofitService)
        }
    }

    private fun showProgressBar() {
        binding.baseProgress.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.baseProgress.visibility = View.GONE
    }

    override fun onMovieClicked(movieId: Int) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra(AppConstants.NavigationExtras.EXTRA_MOVIE_ID, movieId)
        startActivity(intent)
    }


}