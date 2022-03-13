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

/**
 * A factory that returns an instance of [UsersGateway] based on the network
 * connectivity status.
 */
class GatewayProviderImpl(private val context: Context) : GatewayProvider {
    private val database = AppDatabase.instance(context)
    private val httpGateway by lazy { HttpGateway(database) }
    private val cacheGateway by lazy { CacheGateway(database) }

    /**
     * @return a boolean that indicates if the device is connected to the
     * network.
     */
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

    /**
     * @return an [HttpGateway] if the device is connected to the internet.
     * Otherwise, it returns the [CacheGateway].
     */
    override fun userGateway(): UsersGateway = when {
        isOnline() -> httpGateway
        else -> cacheGateway
    }
}
