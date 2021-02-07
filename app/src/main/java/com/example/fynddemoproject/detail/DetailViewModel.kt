package com.example.fynddemoproject.detail

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fynddemoproject.network.data.MovieDetail
import com.example.fynddemoproject.network.remote.RetrofitService

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    val movieDetailData = ObservableField<MovieDetail>()
    val errorData = MutableLiveData<Int>()

    fun getDetails(retrofitService: RetrofitService, movieId: Int?) {
        DetailRepository(retrofitService, this).getMovieDetail(movieId)
    }

    fun addToWishList() {

    }

}