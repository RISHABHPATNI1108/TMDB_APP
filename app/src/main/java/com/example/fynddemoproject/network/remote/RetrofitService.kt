package com.example.fynddemoproject.network.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitService {

    companion object {
        const val API_KEY = "55da82a26a87b7221062dca9f33a4395"
    }

    @GET("/3/authentication/token/new?api_key=$API_KEY")
    fun getAuthenticationToken(): Call<ResponseBody>

    // lists of movies
    @GET("/3/movie/latest")
    fun getLatestMovies(@Query("language") language: String = "en-US"): Call<ResponseBody>

    @GET("/3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Call<ResponseBody>

    @GET("/3/movie/popular")
    fun getPopular(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Call<ResponseBody>

    @GET("/3/movie/top_rated")
    fun getTopRated(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Call<ResponseBody>

    @GET("/3/movie/upcoming")
    fun getUpcoming(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Call<ResponseBody>

    // Movie details and reviews
    @GET("/3//movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<ResponseBody>

    @GET("/3//movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") movieId: String): Call<ResponseBody>

    @GET("/3/movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") movieId: String): Call<ResponseBody>


}