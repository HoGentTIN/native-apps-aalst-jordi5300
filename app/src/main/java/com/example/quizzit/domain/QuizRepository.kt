package com.example.quizzit.domain

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.quizzit.database.QuestionDao
import com.example.quizzit.database.QuizDao
import com.example.quizzit.database.QuizDatabase

class QuizRepository(private val quizDao:QuizDao, private val questionDao: QuestionDao) {


    suspend fun getAllQuizzes(): List<Quiz> {
            return quizDao.getQuizzes()
    }

    fun getAllQuestions(quiz: Quiz): List<Question> {
        return quizDao.getQuestionsFromQuiz(quiz.id)
    }
}