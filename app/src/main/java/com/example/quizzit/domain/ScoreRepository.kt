package com.example.quizzit.domain

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import com.example.quizzit.database.ScoreDao
import com.example.quizzit.network.QuizApiService

class ScoreRepository(
    private val scoreDao: ScoreDao,
    private val quizApiService: QuizApiService,
    private val connectivityManager: ConnectivityManager
) {
    suspend fun getTopScores(id: Int): List<Score> {
        if (connectedToInternet()) {
            val scores = quizApiService.getScores(id)
            //saveInLocalDatabase(scores)
            return scores
        } else {
            return scoreDao.getScores()
        }
    }
    private suspend fun saveInLocalDatabase(scores: List<Score>) {
        scores.forEach {
            scoreDao.insert(it)
        }
    }
    private fun connectedToInternet(): Boolean {
        with(connectivityManager) {
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}