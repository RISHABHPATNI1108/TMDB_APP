package com.example.fynddemoproject.home


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.fynddemoproject.network.data.MoviesList
import com.example.fynddemoproject.network.remote.RetrofitService
import com.example.fynddemoproject.util.AppEnums

open class MovieListDataSourceFactor(val viewModel: HomeViewModel, val movieListType: AppEnums.MovieListType, val retrofitService: RetrofitService) :
    DataSource.Factory<Int, MoviesList>() {

    private val _sourceLiveData = MutableLiveData<MovieDataSource>()
    val sourceLiveData: MutableLiveData<MovieDataSource>
        get() = _sourceLiveData

    private fun getDataSource(): MovieDataSource {
        return MovieDataSource(homeViewModel = viewModel, movieListType , retrofitService)
    }

    override fun create(): DataSource<Int, MoviesList> {
        val source = getDataSource()
        _sourceLiveData.postValue(source)
        return source
    }

}