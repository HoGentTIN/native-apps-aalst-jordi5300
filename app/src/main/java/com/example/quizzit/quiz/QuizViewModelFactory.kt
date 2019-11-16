package com.example.quizzit.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzit.domain.QuizRepository

class QuizViewModelFactory(private val quizRepository: QuizRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
                return QuizViewModel(quizRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
