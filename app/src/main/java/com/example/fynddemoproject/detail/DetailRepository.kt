package com.example.fynddemoproject.detail

import android.annotation.SuppressLint
import com.example.fynddemoproject.R
import com.example.fynddemoproject.network.remote.RetrofitService
import timber.log.Timber
import java.util.concurrent.Executors

class DetailRepository(
    private val retrofitService: RetrofitService,
    private val viewModel: DetailViewModel
) {

    @SuppressLint("CheckResult")
    fun getMovieDetail(movieId: Int?) {
        Executors.newSingleThreadExecutor().execute {
            retrofitService.getMovieDetails(movieId).subscribe({
                viewModel.movieDetailData.set(it)
            }, {
                Timber.e(it)
                viewModel.errorData.postValue(R.string.server_error)
            })
        }
    }

}