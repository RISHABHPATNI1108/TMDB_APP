package com.example.fynddemoproject.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fynddemoproject.R
import com.example.fynddemoproject.extenssions.inTransaction
import dagger.android.AndroidInjection
import timber.log.Timber

open class BaseAppCompatActivity : AppCompatActivity() {

    open fun fragment(): BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    fun addFragment(
        fragment: Fragment?,
        animate: Boolean,
        addToBackStack: Boolean,
        tag: String,
        container: Int = R.id.fragmentContainer,
        checkAdded: Boolean = false
    ) =
        fragment?.let {
            try {
                if (checkAdded && supportFragmentManager.findFragmentByTag(tag) != null) {
                    return@let
                }

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
        tag: String,
        container: Int = R.id.fragmentContainer
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

}