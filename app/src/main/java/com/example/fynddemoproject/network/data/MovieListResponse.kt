package com.example.fynddemoproject.network.data


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    var page: Int,

    @SerializedName("results")
    var results: MutableList<MoviesList>,

    @SerializedName("total_pages")
    var totalPages: Int,

    @SerializedName("total_results")
    var totalResults: Int
) {
}