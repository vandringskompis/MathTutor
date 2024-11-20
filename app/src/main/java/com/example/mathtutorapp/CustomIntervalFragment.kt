package com.example.mathtutorapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class CustomIntervalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_custom_interval, container, false)

        val operator = requireActivity().intent.getStringExtra("selected_operator")

        val highestNumber = view.findViewById<EditText>(R.id.highest_number)
        val lowestNumber = view.findViewById<EditText>(R.id.lowest_number)
        val button = view.findViewById<Button>(R.id.enter_custom_numbers)

        button.setOnClickListener {
            val value1 = lowestNumber.text.toString()
            val value2 = highestNumber.text.toString()


            if (!value1.isDigitsOnly()){
                Toast.makeText(requireContext(), "Input error, please choose numbers.", Toast.LENGTH_LONG).show()
                highestNumber.setText(" ")
                lowestNumber.setText(" ")
            } else if (!value2.isDigitsOnly()) {
                Toast.makeText(requireContext(), "Input error, please choose numbers.", Toast.LENGTH_LONG).show()
                highestNumber.setText(" ")
                lowestNumber.setText(" ")
            } else {


                Log.d("CustomFragment", "Lowest Number: $value1, Highest Number: $value2")

                val intent = Intent(activity, QuestionActivity::class.java).apply {
                    putExtra("lowest_value", value1)
                    putExtra("highest_value", value2)
                    putExtra("operator", operator)

                }

                startActivity(intent)
            }}

        return view
    }

}