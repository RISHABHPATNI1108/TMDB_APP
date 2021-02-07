package com.example.fynddemoproject.util

import androidx.annotation.Keep
import java.io.Serializable


@Keep
sealed class AppEnums : Serializable {

    sealed class MovieListType : AppEnums(), Serializable {
        object POPULAR : MovieListType()
        object TOP_RATED : MovieListType()
        object NOW_PLAYING : MovieListType()
        object UPCOMING : MovieListType()
    }

}
