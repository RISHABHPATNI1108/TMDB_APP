package com.example.fynddemoproject.network.data

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName


@Suppress("UNREACHABLE_CODE")
data class MoviesList(
        @SerializedName("adult")
        var adult: Boolean,

        @SerializedName("backdrop_path")
        var backdropPath: String?,

        @SerializedName("genre_ids")
        var genreIds: List<Double>,

        @SerializedName("id")
        var id: Int,

        @SerializedName("original_language")
        var originalLanguage: String?,

        @SerializedName("original_title")
        var originalTitle: String?,

        @SerializedName("overview")
        var overview: String?,

        @SerializedName("popularity")
        var popularity: Double,

        @SerializedName("poster_path")
        var posterPath: String?,

        @SerializedName("release_date")
        var releaseDate: String?,

        @SerializedName("title")
        var title: String?,

        @SerializedName("video")
        var video: Boolean,

        @SerializedName("vote_average")
        var voteAverage: Double,

        @SerializedName("vote_count")
        var voteCount: Int
) {
    companion object {
        val CALLBACK: DiffUtil.ItemCallback<MoviesList> =
            object : DiffUtil.ItemCallback<MoviesList>() {
                override fun areItemsTheSame(
                        @NonNull moviesList: MoviesList,
                        @NonNull t1: MoviesList
                ): Boolean {
                    return moviesList.id == t1.id
                }

                override fun areContentsTheSame(
                        @NonNull photos: MoviesList,
                        @NonNull t1: MoviesList
                ): Boolean {
                    return true
                }
            }
    }

}
