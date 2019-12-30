package com.example.quizzit.screens.quiz.selecteren

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzit.domain.Quiz
import com.example.quizzit.domain.QuizRepository
import kotlinx.coroutines.launch

class QuizSelecterenViewModel(private val quizRepository: QuizRepository) : ViewModel() {
    val quizzes = MutableLiveData<List<Quiz>>()

    init {
        viewModelScope.launch {
            getQuizzes()
        }
    }

    suspend fun getQuizzes() {
        quizzes.value = quizRepository.getAllQuizzesDatabase()
    }
}
