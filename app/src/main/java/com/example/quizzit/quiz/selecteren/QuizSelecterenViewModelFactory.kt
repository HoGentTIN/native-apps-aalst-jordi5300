package com.example.quizzit.quiz.selecteren

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzit.domain.QuizRepository

class QuizSelecterenViewModelFactory(private val quizRepository: QuizRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizSelecterenViewModel::class.java)) {
            return QuizSelecterenViewModel(
                quizRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
