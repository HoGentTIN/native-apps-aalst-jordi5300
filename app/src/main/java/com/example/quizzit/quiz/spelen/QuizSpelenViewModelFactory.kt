package com.example.quizzit.quiz.spelen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzit.domain.QuizRepository

class QuizSpelenViewModelFactory(private val quizRepository: QuizRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(QuizSpelenViewModel::class.java)) {
                return QuizSpelenViewModel(
                    quizRepository
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
