package com.example.quizzit.domain

import android.net.ConnectivityManager
import com.example.quizzit.database.QuestionDao
import com.example.quizzit.database.QuizDao
import com.example.quizzit.network.QuizApiService

class QuizRepository(
    private val quizDao: QuizDao,
    private val questionDao: QuestionDao,
    private val quizApiService: QuizApiService,
    private val connectivityManager: ConnectivityManager
) {

    suspend fun getAllQuizzes(): List<Quiz> {
        if (connectedToInternet()) {
            val quizzes = quizApiService.getQuizzes()
            saveInLocalDatabase(quizzes)
            return quizzes
        } else {
            return quizDao.getQuizzes()
        }
    }

    suspend fun getAllQuestions(quiz: Quiz): List<Question> {
        if (connectedToInternet()) {
            val questions = quizApiService.getQuestions(quiz.id)
            saveInLocalDatabase2(questions)
            return questions
        } else {
            return questionDao.getQuestionsFromQuiz(quiz.id)
        }
    }

    private suspend fun saveInLocalDatabase(quizzes: List<Quiz>) {
        quizzes.forEach {
            quizDao.insert(it)
        }
    }

    private suspend fun saveInLocalDatabase2(questions: List<Question>) {
        questions.forEach {
            questionDao.insert(it)
        }
    }

    private fun connectedToInternet(): Boolean {
        with(connectivityManager) {
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}
