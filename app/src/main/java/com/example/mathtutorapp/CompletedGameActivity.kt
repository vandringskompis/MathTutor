package com.example.mathtutorapp


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CompletedGameActivity : AppCompatActivity() {

    var winCount = 0
    var lostCount = 0
    lateinit var winMessageTV: TextView
    lateinit var percentageTV :TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_completed_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        winMessageTV = findViewById(R.id.win_message)
        percentageTV = findViewById(R.id.percentage_tv)
        val startButton = findViewById<Button>(R.id.start_button)
        val quitButton = findViewById<Button>(R.id.quit_button)

        startButton.setOnClickListener() {
            val intent = Intent(this, ArithmeticOperationsActivity::class.java)
            startActivity(intent)
        }

        quitButton.setOnClickListener(){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        winCount = intent.getIntExtra("WIN_COUNT", 0)
        lostCount = intent.getIntExtra("LOST_COUNT", 0)


        showStats()

        val correctAnswers = winCount
        val totalQuestions = 10

        val progressBarCircular = findViewById<ProgressBar>(R.id.progressBar)
        val percentage = (correctAnswers.toFloat() / totalQuestions * 100).toInt()

        progressBarCircular.progress = percentage
        percentageTV.text = "$percentage%"

    }

    fun showStats() {

        winMessageTV.text = "You were correct $winCount time(s) and were wrong $lostCount time(s)!"

        Log.d("!!!", "$winMessageTV.text")

    }

}