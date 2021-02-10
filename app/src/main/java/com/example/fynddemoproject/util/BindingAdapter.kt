package com.example.fynddemoproject.util

import android.content.res.ColorStateList
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.fynddemoproject.BuildConfig
import com.example.fynddemoproject.R
import com.example.fynddemoproject.network.data.MovieDetail

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(
        view: AppCompatImageView,
        url: String?
    ) {
        Glide.with(view.context).load(BuildConfig.SMALL_IMAGE_URL + url)
            .placeholder(R.drawable.placeholder_moview_image).into(view)
    }

    @JvmStatic
    @BindingAdapter("productionCompanies")
    fun loadCompanies(
        view: AppCompatTextView,
        companies: List<MovieDetail.ProductionCompany?>?
    ) {
        var string = ""
        companies?.let { it ->
            string += companies[0]?.name
            for (company in it.withIndex()) {
                if (company.index != 0) {
                    string = string + ", " + company.value?.name
                }
            }
        }
        view.text = string
    }

    @JvmStatic
    @BindingAdapter("android:text")
    fun loadCompanies(
        view: AppCompatTextView,
        double: Double?
    ) {
        val string = double?.toString()
        view.text = string
    }

    @JvmStatic
    @BindingAdapter("textColor")
    fun loadTextColor(
        view: AppCompatTextView,
        double: Double?
    ) {
        double?.let {
            if (double <= 5.0f) {
                view.setTextColor(getColor(view.context, R.color.colorSecondary))
                TextViewCompat.setCompoundDrawableTintList(
                    view,
                    ColorStateList.valueOf(getColor(view.context, R.color.colorSecondary))
                )
            } else if (double > 5.0f) {
                view.setTextColor(getColor(view.context, R.color.colorAccent))
                TextViewCompat.setCompoundDrawableTintList(
                    view,
                    ColorStateList.valueOf(getColor(view.context, R.color.colorAccent))
                )
            }
        }
    }


}