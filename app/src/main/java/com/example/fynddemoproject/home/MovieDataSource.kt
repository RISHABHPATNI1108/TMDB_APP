package com.example.fynddemoproject.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.fynddemoproject.network.data.MoviesList
import com.example.fynddemoproject.network.data.NetworkState
import com.example.fynddemoproject.network.remote.RetrofitService
import com.example.fynddemoproject.util.AppEnums
import timber.log.Timber


class MovieDataSource constructor(
    val homeViewModel: HomeViewModel,
    val movieLostType: AppEnums.MovieListType,
    private val retrofitService: RetrofitService
) : PageKeyedDataSource<Int, MoviesList>() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _initialLoad = MutableLiveData<NetworkState>()
    val initialLoad: LiveData<NetworkState>
        get() = _initialLoad

    /**
     * This callback loads the very first page of the response.
     *
     */
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MoviesList>
    ) {
        _networkState.postValue(NetworkState.LOADING)
        _initialLoad.postValue(NetworkState.LOADING)

        when (movieLostType) {
            AppEnums.MovieListType.UPCOMING -> {
                retrofitService.getUpcoming(page = 1).subscribe({
                    callback.onResult(it.results, null, 2)
                    _networkState.postValue(NetworkState.LOADED)
                    _initialLoad.postValue(NetworkState.LOADED)
                }, {
                    Timber.e(it)
                    homeViewModel.setError(it.message)
                    _networkState.postValue(NetworkState.error(it.message))
                    _initialLoad.postValue(NetworkState.error(it.message))
                })
            }
            AppEnums.MovieListType.POPULAR -> {
                retrofitService.getPopular(page = 1).subscribe({
                    callback.onResult(it.results, null, 2)
                    _networkState.postValue(NetworkState.LOADED)
                    _initialLoad.postValue(NetworkState.LOADED)
                }, {
                    homeViewModel.setError(it.message)
                    _networkState.postValue(NetworkState.error(it.message))
                    _initialLoad.postValue(NetworkState.error(it.message))
                })
            }
            AppEnums.MovieListType.NOW_PLAYING -> {
                retrofitService.getNowPlayingMovies(page = 1).subscribe({
                    callback.onResult(it.results, null, 2)
                    _networkState.postValue(NetworkState.LOADED)
                    _initialLoad.postValue(NetworkState.LOADED)
                }, {
                    homeViewModel.setError(it.message)
                    _networkState.postValue(NetworkState.error(it.message))
                    _initialLoad.postValue(NetworkState.error(it.message))
                })
            }
            AppEnums.MovieListType.TOP_RATED -> {
                retrofitService.getTopRated(page = 1).subscribe({
                    callback.onResult(it.results, null, 2)
                    _networkState.postValue(NetworkState.LOADED)
                    _initialLoad.postValue(NetworkState.LOADED)
                }, {
                    homeViewModel.setError(it.message)
                    _networkState.postValue(NetworkState.error(it.message))
                    _initialLoad.postValue(NetworkState.error(it.message))
                })
            }
        }

    }


    /**
     * This callback loads the subsequent pages.
     *
     */
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MoviesList>
    ) {
        _networkState.postValue(NetworkState.LOADING)
        when (movieLostType) {
            AppEnums.MovieListType.UPCOMING -> {
                retrofitService.getUpcoming(page = params.key).subscribe({
                    callback.onResult(
                        it.results,
                        params.key.inc()
                    )
                    _networkState.postValue(NetworkState.LOADED)
                }, {
                    _networkState.postValue(NetworkState.error(it.message))
                    homeViewModel.setError(it.message)
                })
            }
            AppEnums.MovieListType.POPULAR -> {

                retrofitService.getPopular(page = 1).subscribe({
                    callback.onResult(it.results, params.key.inc())
                    _networkState.postValue(NetworkState.LOADED)
                }, {
                    _networkState.postValue(NetworkState.error(it.message))
                    homeViewModel.setError(it.message)
                })
            }
            AppEnums.MovieListType.NOW_PLAYING -> {
                retrofitService.getNowPlayingMovies(page = params.key).subscribe({
                    callback.onResult(it.results, params.key.inc())
                    _networkState.postValue(NetworkState.LOADED)
                }, {
                    _networkState.postValue(NetworkState.error(it.message))
                    homeViewModel.setError(it.message)
                })
            }
            AppEnums.MovieListType.TOP_RATED -> {
                retrofitService.getTopRated(page = params.key).subscribe({
                    callback.onResult(it.results, params.key.inc())
                    _networkState.postValue(NetworkState.LOADED)
                }, {
                    _networkState.postValue(NetworkState.error(it.message))
                    homeViewModel.setError(it.message)
                })
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MoviesList>
    ) {
    }
}