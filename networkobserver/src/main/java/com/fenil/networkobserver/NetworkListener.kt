package com.fenil.networkobserver

interface NetworkListener {
    fun onNetworkAvailable()
    fun onNetworkLoss()
}