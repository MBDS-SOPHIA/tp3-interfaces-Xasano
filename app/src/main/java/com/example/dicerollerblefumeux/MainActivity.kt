package com.example.dicerollerblefumeux

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

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
        val input_target: TextInputEditText = findViewById(R.id.target_number)
        val rollButton: Button = findViewById(R.id.button)
        input_target.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                rollButton.isEnabled = !s.isNullOrEmpty()
        }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        rollButton.setOnClickListener { rollDice() }
    }

    private fun rollDice() {
        val dice1 = Dice(6)
        val dice2 = Dice(6)
        val resultd1: TextView = findViewById(R.id.d1)
        val sumd1 = dice1.roll().toString()
        resultd1.text = sumd1
        val resultd2: TextView = findViewById(R.id.d2)
        val sumd2 = dice2.roll().toString()
        resultd2.text = sumd2
        if (sumd1.toInt() + sumd2.toInt() == findViewById<TextInputEditText>(R.id.target_number).text.toString().toInt()) {
            Toast.makeText(applicationContext, "Victoire !", Toast.LENGTH_SHORT).show()
        }
    }
}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}