package com.fenil.networkobserver

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner

class NetworkObserver(
    context: Context,
    networkListener: NetworkListener,
    lifecycleOwner: LifecycleOwner
) {
    init {
        val networkConnection = NetworkConnection(context)

        networkConnection.observe(lifecycleOwner) { isConnected ->
            if (isConnected) {
                networkListener.onNetworkAvailable()

            } else {
                networkListener.onNetworkLoss()
            }
        }
    }
}