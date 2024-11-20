package com.example.mathtutorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    lateinit var appName : TextView
    lateinit var welcomeMessage : TextView
    lateinit var infoMessage : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val startButton : Button = findViewById(R.id.start_button)
        startButton.setOnClickListener(){
            val intent = Intent(this, ArithmeticOperationsActivity::class.java)
            startActivity(intent)

        }
        appName = findViewById(R.id.app_title)
        welcomeMessage = findViewById(R.id.welcom_message)
        infoMessage = findViewById(R.id.info_message)
    }
}