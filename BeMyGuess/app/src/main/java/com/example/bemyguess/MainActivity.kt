package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var answer : Int = 0
    var isGameOver : Boolean = false
    var guesses : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startOver()
    }

    fun generateAnswer() {
        answer = Random.nextInt(1, 25)
    }

    fun startOver() {
        isGameOver = false
        guesses = 0

        generateAnswer()

        val promptTextView : TextView = findViewById<TextView>(R.id.textView)
        promptTextView.text = "Guess a number from 1 to 25"

        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "No. of Guesses: " + guesses.toString()
    }

    fun btnStartOverTapped(view: View) {
        startOver()
    }

    fun btnSubmitTapped(view: View) {
        val guess = getUsersGuess() ?: -999
        val promptTextView : TextView = findViewById<TextView>(R.id.textView)

        if (guess !in 1..25) {
            "Guess must be 1 to 25".also { promptTextView.text = it }

            guesses++

            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = "No. of Guesses: " + guesses.toString()

            return
        }

        isGameOver = true

        promptTextView.text = "You guessed the number!"

        val answerTextView : TextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = answer.toString()
    }

    fun getUsersGuess() : Int? {
        val editTextGuess : EditText = findViewById<EditText>(R.id.editTextGuess)
        val usersGuess : String = editTextGuess.text.toString()

        var guessAsInt : Int = 0

        try {
            guessAsInt = usersGuess.toInt()
        }
        catch (e: NumberFormatException)
        {
            return null
        }

        return guessAsInt
    }
}
