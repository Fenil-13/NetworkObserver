package com.fenil.networkobserver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData

/***
 * Here NetworkConnection is class which extended as LiveData and used for observing network in android
 * for Greater than VERSION_CODE M, use network callback and other use broadcast receiver
 */
const val TAG="NetworkObserver"
internal class NetworkConnection(private val context: Context) : LiveData<Boolean>() {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d(TAG, "network status : available")
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d(TAG, "network status : lost")
            postValue(false)
        }
    }
    private val networkReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if(isInternetConnected(context)){
                Log.d(TAG, "network status : available")
                postValue(true)
            }else{
                Log.d(TAG, "network status : lost")
                postValue(false)
            }
        }
    }
    override fun onActive() {
        super.onActive()
        Log.d(TAG, "observer attaching")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        }else{
            val intentFilter =@Suppress("DEPRECATION") IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            context.registerReceiver(networkReceiver, intentFilter)
        }
        Log.d(TAG, "observer attached")
    }

    override fun onInactive() {
        super.onInactive()
        Log.d(TAG, "observer detaching")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }else{
            context.unregisterReceiver(networkReceiver)
        }
        Log.d(TAG, "observer detached")
    }

    companion object{
        @Suppress("DEPRECATION")
        fun isInternetConnected(context: Context):Boolean{
            val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            try {
                val info=connectivityManager.allNetworkInfo
                for(networkInfo: NetworkInfo in info){
                    if(networkInfo.state== NetworkInfo.State.CONNECTED){
                        return true
                    }
                }
            }catch (e:Exception){
                Log.d(TAG, "Error - check_internet_status - ${e.message}")
                e.printStackTrace()
                return false
            }
            return false


        }
    }

}