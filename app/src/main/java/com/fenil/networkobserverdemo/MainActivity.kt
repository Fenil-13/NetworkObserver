package com.fenil.networkobserverdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.fenil.networkobserver.NetworkListener
import com.fenil.networkobserver.NetworkObserver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            NetworkObserver.getInstance(applicationContext,object :NetworkListener{
                override fun onNetworkAvailable() {
                    Toast.makeText(this@MainActivity, "Network is Available", Toast.LENGTH_SHORT).show()
                }

                override fun onNetworkLoss() {
                    Toast.makeText(this@MainActivity, "Network is Loss`", Toast.LENGTH_SHORT).show()
                }

            }, this)
    }
}