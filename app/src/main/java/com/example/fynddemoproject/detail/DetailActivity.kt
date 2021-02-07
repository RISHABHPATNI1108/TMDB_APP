package com.example.fynddemoproject.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fynddemoproject.R
import com.example.fynddemoproject.base.BaseAppCompatActivity
import com.example.fynddemoproject.databinding.ActivityDetailBinding
import com.example.fynddemoproject.network.remote.RetrofitService
import com.example.fynddemoproject.util.AppConstants
import javax.inject.Inject

class DetailActivity : BaseAppCompatActivity() {

    private var movieId: Int? = null
    private lateinit var binding: ActivityDetailBinding
    private val viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        .create(DetailViewModel::class.java)

    @Inject
    lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        movieId = intent.getIntExtra(AppConstants.NavigationExtras.EXTRA_MOVIE_ID , 0)
        binding.vm = viewModel
        viewModel.getDetails(retrofitService, movieId)
        //showProgressBar()
        initializeObservers()
    }

    private fun initializeObservers() {

        viewModel.errorData.observe(this, {
            hideProgressBar()
            showLongSnackBar(binding.constraintLayout, it)
        })

    }

    private fun showProgressBar() {
        binding.baseProgress.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.baseProgress.visibility = View.GONE
    }

}