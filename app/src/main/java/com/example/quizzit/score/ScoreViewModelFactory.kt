package com.example.quizzit.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzit.domain.ScoreRepository

class ScoreViewModelFactory(private val scoreRepository: ScoreRepository, private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(scoreRepository, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
