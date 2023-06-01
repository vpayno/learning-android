package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var answer : Int = 0
    var isGameOver : Boolean = false
    var numOfAttempts : Int = 0

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
        numOfAttempts = 0

        generateAnswer()

        val promptTextView : TextView = findViewById<TextView>(R.id.textView)
        promptTextView.text = "Guess from 1 to 25"

        val answerTextView : TextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"

        val editTextGuess : EditText = findViewById<EditText>(R.id.editTextGuess)
        editTextGuess.text.clear()

        val submitButton : Button = findViewById<Button>(R.id.buttonSubmit)
        submitButton.isEnabled = true
    }

    fun btnStartOverTapped(view: View) {
        startOver()
    }

    fun btnSubmitTapped(view: View) {
        val guess = getUsersGuess() ?: -999
        val promptTextView : TextView = findViewById<TextView>(R.id.textView)

        // not a valid guess
        if (guess !in 1..25) {
            "Guess must be 1 to 25".also { promptTextView.text = it }

            numOfAttempts++

            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = "No. of Guesses: " + numOfAttempts.toString()

            return
        }

        // we have a valid guess

        numOfAttempts++

        var message : String = ""
        var plural : String = if (numOfAttempts > 1) "es" else ""


        if (guess == answer) {
            isGameOver = true

            message = "Correct! Guess" + plural + ": $numOfAttempts"
            promptTextView.text = message

            val answerTextView : TextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = answer.toString()

            val submitButton : Button = findViewById<Button>(R.id.buttonSubmit)
            submitButton.isEnabled = false
        }
        else {
            message = if (guess < answer) "Guess too low!" else "Guess too high!"
            promptTextView.text = message
        }
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
