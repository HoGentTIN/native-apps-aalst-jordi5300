package com.example.quizzit.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzit.domain.Score
import com.example.quizzit.domain.ScoreRepository
import kotlinx.coroutines.launch

class ScoreViewModel(private val scoreRepository: ScoreRepository, private val id: Int) :
    ViewModel() {
    val scores = MutableLiveData<List<Score>>()

    init {
        viewModelScope.launch {
            getScores(id)
        }
    }

    suspend fun getScores(id: Int) {
        scores.value = scoreRepository.getTopScores(id)
    }
}