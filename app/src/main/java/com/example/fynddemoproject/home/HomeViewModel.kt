package com.example.fynddemoproject.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.fynddemoproject.network.data.MoviesList
import com.example.fynddemoproject.network.remote.RetrofitService
import com.example.fynddemoproject.util.AppEnums


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var movieDataSourceFactory: MovieListDataSourceFactor
    var dataSourceMutableLiveData: MutableLiveData<MovieDataSource>? = null
    var pagedListLiveData: LiveData<PagedList<MoviesList>>? = null

    fun setMovieType(movieListType: AppEnums.MovieListType, retrofitService: RetrofitService) {

        movieDataSourceFactory =
            MovieListDataSourceFactor(this, movieListType, retrofitService)
        dataSourceMutableLiveData = movieDataSourceFactory.sourceLiveData

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(20 * 2)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build()

        pagedListLiveData = LivePagedListBuilder(movieDataSourceFactory, config)
            .build()

    }


    val errorMutableLiveData = MutableLiveData<String?>()

    fun setError(error: String?) {
        errorMutableLiveData.postValue(error)
    }


}