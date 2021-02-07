package com.example.fynddemoproject.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fynddemoproject.R
import com.example.fynddemoproject.extenssions.inTransaction
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import timber.log.Timber

open class BaseAppCompatActivity : AppCompatActivity() {

    open fun initializeAppBar() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    fun addFragment(
        fragment: Fragment?,
        animate: Boolean,
        addToBackStack: Boolean,
        tag: String?,
        container: Int = R.id.fragment_container,
    ) =
        fragment?.let {
            try {

                val transaction = supportFragmentManager.beginTransaction()
                if (animate)
                    transaction.setCustomAnimations(
                        R.animator.slide_in_up,
                        0,
                        0,
                        R.animator.slide_in_down
                    )
                transaction.add(
                    container, fragment, tag
                )
                if (addToBackStack)
                    transaction.addToBackStack(tag)
                else
                    transaction.addToBackStack(null)

                supportFragmentManager.inTransaction {
                    transaction
                }
            } catch (ex: Exception) {
                Timber.e(ex)
            }
        }

    fun replaceFragmentWithFadeInTransition(
        fragment: Fragment?,
        animate: Boolean,
        addToBackStack: Boolean,
        tag: String?,
        container: Int = R.id.fragment_container
    ) =
        fragment?.let {
            try {
                val transaction = supportFragmentManager.beginTransaction()
                if (animate)
                    transaction.setCustomAnimations(
                        R.anim.fade_in,
                        0,
                        0,
                        R.anim.fade_out
                    )
                transaction.replace(
                    container, fragment, tag
                )
                if (addToBackStack)
                    transaction.addToBackStack(tag)
                supportFragmentManager.inTransaction {
                    transaction
                }
            } catch (ex: IllegalStateException) {
                Timber.e(ex)
            }
        }

    fun showLongSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    fun showLongSnackBar(view: View, message: Int) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    fun showLongToast(view: View, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showLongToast(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}