package com.example.fynddemoproject.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fynddemoproject.R
import com.example.fynddemoproject.databinding.LayoutCardMovieBinding
import com.example.fynddemoproject.network.data.MoviesList
import timber.log.Timber
import java.util.*


class ListAdapter(private val itemClickListener :ItemClickListener) : PagedListAdapter<MoviesList, ListAdapter.ListViewHolder>(object : DiffUtil.ItemCallback<MoviesList>() {
    override fun areContentsTheSame(oldItem: MoviesList, newItem: MoviesList): Boolean =
        Objects.equals(oldItem, newItem)

    override fun areItemsTheSame(oldItem: MoviesList, newItem: MoviesList): Boolean =
        oldItem.id == newItem.id
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardBindingModule = DataBindingUtil.inflate<LayoutCardMovieBinding>(
            layoutInflater,
            R.layout.layout_card_movie,
            parent,
            false
        )
        return ListViewHolder(cardBindingModule, itemClickListener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        Timber.d("itemCount ${super.getItemCount()}")
        return super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        Timber.d("itemViewType ${super.getItemCount()}")
        return super.getItemViewType(position)
    }

    class ListViewHolder(private val cardBinding: LayoutCardMovieBinding, 
                         private val itemClickListener: ItemClickListener) :
        RecyclerView.ViewHolder(cardBinding.root) {
        
        fun bind(moviesList: MoviesList?) {
            cardBinding.movie = moviesList
            cardBinding.listener = itemClickListener
        }

    }

    interface ItemClickListener {

        fun onMovieClicked(movieId: Int)

    }

}