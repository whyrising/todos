package com.github.whyrising.todos.gateway

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_MOBILE
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build
import com.github.whyrising.todos.core.GatewayProvider
import com.github.whyrising.todos.core.UsersGateway

class GatewayProviderImpl(private val context: Context) : GatewayProvider {
    private val userDao by lazy { AppDatabase.instance(context).userDao() }
    private val httpGateway by lazy { HttpGateway(userDao) }
    private val cacheGateway by lazy { CacheGateway(userDao) }

    private fun isOnline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                if (capabilities == null) false else
                    capabilities.hasTransport(TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(TRANSPORT_WIFI) ||
                        capabilities.hasTransport(TRANSPORT_ETHERNET)
            }
            else -> {
                val activeNetwork = cm.activeNetworkInfo
                if (activeNetwork == null) false else
                    (activeNetwork.type == TYPE_WIFI) ||
                        (activeNetwork.type == TYPE_MOBILE) ||
                        (activeNetwork.type == TYPE_ETHERNET)
            }
        }
    }

    override fun userGateway(): UsersGateway = when {
        isOnline() -> httpGateway
        else -> cacheGateway
    }
}
