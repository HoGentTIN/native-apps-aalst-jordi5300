package com.example.quizzit.domain

import android.content.Context
import com.example.quizzit.database.QuizDao
import com.example.quizzit.database.QuizDatabase

class QuizRepository(private val quizDao:QuizDao) {


    suspend fun getAllQuizzes(): List<Quiz> {
            return quizDao.getQuizzes()
    }
    suspend fun insert(quiz: Quiz) = quizDao.insert(quiz)
}