package com.example.mathtutorapp

import androidx.activity.enableEdgeToEdge

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ArithmeticOperationsActivity : AppCompatActivity() {

    lateinit var operatorTitle: TextView
    lateinit var operatorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_arithmetic_operations)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        operatorMessage = findViewById(R.id.operator_message)
        operatorTitle = findViewById(R.id.operator_title)

        var backButton : Button = findViewById(R.id.back_button)
        backButton.setOnClickListener() {
            finish()
        }

        val buttonListener = View.OnClickListener { view: View? ->
            val intent = Intent(this, IntervalActivity::class.java)
            val operator = when (view?.id) {
                R.id.operator1_button -> "+"
                R.id.operator2_button -> "-"
                R.id.operator3_button -> "*"
                R.id.operator4_button -> "/"
                else -> "+"
            }
            intent.putExtra("selected_operator", operator)
            startActivity(intent)
        }
        val operator1Button = findViewById<Button>(R.id.operator1_button)
        operator1Button.setOnClickListener(buttonListener)
        val operator2Button = findViewById<Button>(R.id.operator2_button)
        operator2Button.setOnClickListener(buttonListener)
        val operator3Button = findViewById<Button>(R.id.operator3_button)
        operator3Button.setOnClickListener(buttonListener)
        val operator4Button = findViewById<Button>(R.id.operator4_button)
        operator4Button.setOnClickListener(buttonListener)
    }
}