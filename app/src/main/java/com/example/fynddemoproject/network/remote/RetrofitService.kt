package com.example.fynddemoproject.network.remote

import com.example.fynddemoproject.BuildConfig
import com.example.fynddemoproject.network.data.MovieDetail
import com.example.fynddemoproject.network.data.MovieListResponse
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitService {

    @GET("/3/authentication/token/new")
    fun getAuthenticationToken(): Call<ResponseBody>

    // lists of movies
    @GET("/3/movie/latest")
    fun getLatestMovies(@Query("language") language: String = "en-US"): Deferred<ResponseBody>

    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMBD_API_KEY
    ): Observable<MovieListResponse>

    @GET("/3/movie/popular")
    fun getPopular(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): Observable<MovieListResponse>

    @GET("/3/movie/top_rated")
    fun getTopRated(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMBD_API_KEY
    ): Observable<MovieListResponse>

    @GET("/3/movie/upcoming")
    fun getUpcoming(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMBD_API_KEY
    ): Observable<MovieListResponse>

    // Movie details and reviews
    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") apiKey: String = BuildConfig.TMBD_API_KEY
    ): Single<MovieDetail>

    @GET("/3//movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") movieId: String): Call<ResponseBody>

    @GET("/3/movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") movieId: String): Call<ResponseBody>


}