package com.example.mathtutorapp


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class IntervalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_interval)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val operator = intent.getStringExtra("selected_operator")

        var backButton : Button = findViewById(R.id.back_button)
        backButton.setOnClickListener() {
            finish()
        }


        val button: Button = findViewById(R.id.button4)
        button.setOnClickListener {
            val intent = Intent(this, CustomIntervalFragment::class.java)
            val newFragment = CustomIntervalFragment()
            val transaction = supportFragmentManager.beginTransaction()
            intent.putExtra("operator", operator)
            transaction.replace(R.id.fragment_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }


        val buttonListener = View.OnClickListener { view: View? ->
            val intent = Intent(this, QuestionActivity::class.java)
            val interval = when (view?.id) {
                R.id.button -> "0-10"
                R.id.button2 -> "0-100"
                R.id.button3 -> "0-1000"
                else -> "0"
            }
            intent.putExtra("selected_interval", interval)
            intent.putExtra("operator", operator)
            startActivity(intent)
        }
        val interval1Button = findViewById<Button>(R.id.button)
        interval1Button.setOnClickListener(buttonListener)
        val interval2Button = findViewById<Button>(R.id.button2)
        interval2Button.setOnClickListener(buttonListener)
        val interval3Button = findViewById<Button>(R.id.button3)
        interval3Button.setOnClickListener(buttonListener)

    }


}