package com.sample.ktln01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("tag_log", "SecondActivity_Create")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val offerViewText = findViewById<TextView>(R.id.tvOffer)
        val username = intent.getStringExtra("USER")
        val message = "$username, you will get free access to all contents for one month"
        offerViewText.text = message
    }

    override fun onStart() {
        super.onStart()
        Log.i("tag_log", "SecondActivity_Start")
    }

    override fun onResume() {
        super.onResume()
        Log.i("tag_log", "SecondActivity_Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("tag_log", "SecondActivity_Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("tag_log", "SecondActivity_Stop")
    }

    override fun onRestart() {
        Log.i("tag_log", "SecondActivity_restart")
        super.onRestart()
    }

    override fun onDestroy() {
        Log.i("tag_log", "SecondActivity_destroy")
        super.onDestroy()
    }
}