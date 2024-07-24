package com.sample.ktln01

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var sp: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            Log.i("tag_log", "MainActivity_Create")
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            val tv = findViewById<TextView>(R.id.tvMessage)
            val btn = findViewById<Button>(R.id.btnSend)
            val offerButton = findViewById<Button>(R.id.btnOffer)
            val et = findViewById<EditText>(R.id.etName)

            var enteredName = ""

            sp = getSharedPreferences("my_SharePreferences", MODE_PRIVATE)
            editor = sp.edit()

            btn.setOnClickListener {
                enteredName = et.text.toString()
                if (enteredName == "") {
                    tv.text = ""
                    offerButton.visibility = INVISIBLE
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter a name.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val message = "Hello $enteredName, welcome to Android programming world."
                    tv.text = message
                    et.text.clear()
                    offerButton.visibility = VISIBLE
                }
            }

            offerButton.setOnClickListener {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("USER", enteredName)
                startActivity(intent)
            }
        }
        catch (e: Exception)
        {
            Log.i("tag_log", "ERROR: " + e.message.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("tag_log", "MainActivity_Start")
    }

    override fun onResume() {
        super.onResume()
        Log.i("tag_log", "MainActivity_Resume")

        val name = sp.getString("sp_username", null)
        val et = findViewById<EditText>(R.id.etName)
        et.setText(name)
        Toast.makeText(
            this@MainActivity,
            "Welcome back $name",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onPause() {
        super.onPause()
        Log.i("tag_log", "MainActivity_Pause")

        val et = findViewById<EditText>(R.id.etName)
        val name = et.text.toString()
        editor.apply {
            putString("sp_username", name)
            commit()
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i("tag_log", "MainActivity_Stop")
    }

    override fun onRestart() {
        Log.i("tag_log", "MainActivity_restart")
        super.onRestart()
    }
}