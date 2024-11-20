package com.example.mathtutorapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class QuestionActivity : AppCompatActivity() {

    lateinit var question: TextView
    lateinit var answerInput: EditText

    lateinit var answerView: TextView
    lateinit var correctWrongTV: TextView
    lateinit var nextQuestionButton: Button
    var winCount = 0
    var lostCount = 0
    var gameCount = 0
    var correctAnswer = "You were correct!"
    var wrongAnswer = "You were wrong. Correct answer is:"
    var randomNumber1: Int = 0
    var randomNumber2: Int = 0
    var operator: String? = ""
    var answer: Double = 0.0
    var lowestNumber = 0
    var highestNumber = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val enterButton = findViewById<Button>(R.id.enter_button)


        var backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener() {
            finish()
        }

        question = findViewById(R.id.question_view)
        answerInput = findViewById(R.id.answer_input)
        answerView = findViewById(R.id.answer_view)
        correctWrongTV = findViewById(R.id.correct_wrong_view)
        nextQuestionButton = findViewById(R.id.next_question)


        operator = intent.getStringExtra("operator")
        val interval = intent.getStringExtra("selected_interval") ?: "no"

        val lowestValueString = intent.getStringExtra("lowest_value") ?: "no"
        val highestValueString = intent.getStringExtra("highest_value") ?: "no"
        lowestNumber = lowestValueString.toIntOrNull() ?: 0
        highestNumber = highestValueString.toIntOrNull() ?: 0




        if (interval != "no") {
            val (start, end) = interval.split("-").map {
                it.toInt()
            }

            lowestNumber = start
            highestNumber = end

            generateRandomNumbers()
            generateQuestions()
            setQuestion()

        } else if (lowestValueString != "no" && highestValueString != "no") {
            generateRandomNumbers()
            generateQuestions()
            setQuestion()

        } else {

        }

        enterButton.setOnClickListener {
            if (answerInput.text.isNotEmpty()) {
                var userAnswer = try {answerInput.text.toString().toDouble()

                }catch (e: NumberFormatException){
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val randomNumber1Double = randomNumber1.toDouble()
                val randomNumber2Double = randomNumber2.toDouble()
                if (gameCount < 10) {

                    if (operator == "+") {
                        answer = randomNumber1Double + randomNumber2Double
                    } else if (operator == "-") {
                        answer = randomNumber1Double - randomNumber2Double
                    } else if (operator == "*") {
                        answer = randomNumber1Double * randomNumber2Double
                    } else if (operator == "/") {

                        answer = randomNumber1Double / randomNumber2Double
                    }
                } else {
                }

                if (userAnswer == answer) {

                    correct()
                    setAnswer()
                    gameCount++

                    if (gameCount < 10) {
                        nextQuestionButton.visibility = View.VISIBLE
                        nextQuestionButton.isClickable = true
                    }

                    if (gameCount > 9) {

                        enterButton.text = "Next"
                        override@
                        enterButton.setOnClickListener() {

                            val intent = Intent(this, CompletedGameActivity::class.java)

                            intent.putExtra("WIN_COUNT", winCount)
                            intent.putExtra("LOST_COUNT", lostCount)
                            startActivity(intent)
                        }

                    }
                } else {
                    wrong()
                    setAnswer()
                    gameCount++

                    if (gameCount < 10) {
                        nextQuestionButton.visibility = View.VISIBLE
                        nextQuestionButton.isClickable = true
                    }

                    if (gameCount > 9) {

                        enterButton.text = "Next"
                        override@
                        enterButton.setOnClickListener() {
                            val intent = Intent(this, CompletedGameActivity::class.java)

                            intent.putExtra("WIN_COUNT", winCount)
                            intent.putExtra("LOST_COUNT", lostCount)
                            startActivity(intent)

                            Log.d("!!!", "$winCount $lostCount")
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Answer field can't be empty!", Toast.LENGTH_SHORT)
                    .show()
            }

            nextQuestionButton.setOnClickListener() {
                answerInput.setText(" ")
                correctWrongTV.text = " "
                answerView.text = " "
                generateRandomNumbers()
                generateQuestions()
                setQuestion()
                nextQuestionButton.visibility = View.INVISIBLE
                nextQuestionButton.isClickable = false

            }

        }
    }

    fun generateQuestions(): String {
        return "$randomNumber1 $operator $randomNumber2"

    }

    fun setQuestion() {

        val generatedQuestion = generateQuestions()
        question.text = generatedQuestion

    }

    fun setAnswer() {
        answerView.text = answer.toString()
    }

    fun correct() {
        correctWrongTV.text = correctAnswer
        winCount++


    }

    fun wrong() {
        correctWrongTV.text = wrongAnswer
        lostCount++

    }


    fun generateRandomNumbers() {
        randomNumber1 = (lowestNumber + Math.random() * (highestNumber - lowestNumber)).toInt()
        randomNumber2 = (lowestNumber + Math.random() * (highestNumber - lowestNumber)).toInt()
    }


}