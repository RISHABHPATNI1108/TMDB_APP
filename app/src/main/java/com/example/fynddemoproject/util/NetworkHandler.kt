package com.example.fynddemoproject.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Injectable class which returns information about the network connection state.
 */
@Singleton
@Suppress("DEPRECATION")
class NetworkHandler
@Inject constructor(private val context: Context) {

    private fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    return when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    return when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_VPN -> true
                        ConnectivityManager.TYPE_BLUETOOTH -> true
                        else -> false
                    }
                }
            }
        }
        return false
    }

    val isConnected: Boolean = isNetworkConnected()

}