package com.example.fynddemoproject.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection

open class BaseFragment : Fragment() {

    open fun initializeViews() {}
    open fun initializeObservers() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        initializeObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    fun showLongSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    fun showLongSnackBar(view: View, message: Int) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    fun showLongToast(view: View, message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    fun showLongToast(message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }


}