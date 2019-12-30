package com.example.quizzit.activities

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.quizzit.domain.Quiz
import com.example.quizzit.domain.QuizRepository
import kotlinx.coroutines.launch

class LoadingViewModel(application: Application, private val quizRepository: QuizRepository) : AndroidViewModel(application) {

    private val _loadingResult = MutableLiveData<Int>()
    var quizzes = listOf<Quiz>()
    val isLoading: LiveData<Int>
        get() = _loadingResult

    init {
        viewModelScope.launch {
            try {
                quizzes = quizRepository.getAllQuizzes()
                quizzes.forEach {
                    quizRepository.getAllQuestions(it)
                }
                _loadingResult.value = Activity.RESULT_OK
            }catch (e: Exception){
                _loadingResult.value = Activity.RESULT_CANCELED
            }
            }
        }
}


