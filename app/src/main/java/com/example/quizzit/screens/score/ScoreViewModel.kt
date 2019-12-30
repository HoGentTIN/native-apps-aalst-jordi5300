package com.example.quizzit.screens.score

import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzit.domain.Score
import com.example.quizzit.domain.ScoreRepository
import kotlinx.coroutines.launch

class ScoreViewModel(private val scoreRepository: ScoreRepository, private val id: Int = 0) : ViewModel() {
    val scores = MutableLiveData<List<Score>>()

    init {
        viewModelScope.launch {
            getScores(id)
        }
    }

    suspend fun getScores(id: Int) {
        scores.value = scoreRepository.getTopScores(id)
    }

    fun postScore(id: Int, nicknaam: String, punten: Int, tijd: String) {
        viewModelScope.launch {
            scoreRepository.postScore(id, nicknaam, punten, tijd)
        }
    }

    fun connectedToInternet(con: ConnectivityManager): Boolean {
        with(con) {
            return con.activeNetworkInfo != null && con.activeNetworkInfo.isConnected
        }
    }
}
