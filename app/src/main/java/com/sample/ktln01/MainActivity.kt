package com.sample.ktln01

import android.content.Intent
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tv = findViewById<TextView>(R.id.tvMessage)
        val  et = findViewById<EditText>(R.id.etName)
        val btn = findViewById<Button>(R.id.btnSend)
        val offerButton = findViewById<Button>(R.id.btnOffer)

        var enteredName = ""

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
                val message = "Hello $enteredName, wellcome to Android programming world."
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
}