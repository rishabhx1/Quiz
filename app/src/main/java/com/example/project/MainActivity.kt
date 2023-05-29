package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: GridRadioGroup
    private lateinit var nextButton: TextView

    private var currentQuestionIndex = 0
    private var points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionBlock)
        optionsRadioGroup = findViewById(R.id.optionsRG)
        nextButton = findViewById(R.id.nextBT)

        currentQuestionIndex = 0

        val quizQuestions = listOf(
            QuizData("Question 1", listOf("option 1", "option 2", "option 3", "option 4"), 0),
            QuizData("Question 2", listOf("option 1", "option 2", "option 3", "option 4"), 1),
            QuizData("Question 3", listOf("option 1", "option 2", "option 3", "option 4"), 3)
        )

        displayQuestion(currentQuestionIndex, quizQuestions)
        // Set a click listener for the Next button
        nextButton.setOnClickListener {
            // Handle next button click
            currentQuestionIndex++
            if (currentQuestionIndex < quizQuestions.size) {
                displayQuestion(currentQuestionIndex, quizQuestions)
            } else {
                Toast.makeText(applicationContext, points.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayQuestion(questionIndex: Int, quizQuestions: List<QuizData>) {
        val currentQuestion = quizQuestions[questionIndex]

        // Set the question text
        questionTextView.text = currentQuestion.question

        // Add options to radio buttons
        val options = currentQuestion.options
        for (i in options.indices) {
            val radioButton = optionsRadioGroup.getChildAt(i) as RadioButton
            radioButton.text = options[i]
        }

        // Enable the Next button
        nextButton.isEnabled = true
    }
}

