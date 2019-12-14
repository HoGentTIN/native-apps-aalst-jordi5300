package com.example.quizzit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizzit.domain.Question
import com.example.quizzit.domain.Quiz
import com.example.quizzit.domain.Score
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(
    entities = [Quiz::class, Question::class, Score::class],
    version = 1,
    exportSchema = false
)
abstract class QuizDatabase : RoomDatabase() {
    abstract val quizDao: QuizDao
    abstract val questionDao: QuestionDao
    abstract val scoreDao: ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuizDatabase::class.java,
                        "quiz_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    instance.putQuizzes()
                    instance.putQuestions()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private fun putQuizzes() {
        val quizList = listOf(
            Quiz(1, "quiz1", "algemeen"),
            Quiz(2, "quiz2", "algemeen2")
        )

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                quizList.map { it }
                    .map { quizDao.insert(it) }
            }
        }
    }

    private fun putQuestions() {
        val questionList = listOf(
            Question(
                1, 1,
                vraag = "Wanneer werd John F. Kennedy vermoord?",
                keuze1 = "1961",
                keuze2 = "1965",
                keuze3 = "1967",
                antwoord = "1963"
            ),
            Question(
                2, 1,
                vraag = "Welke diameter hadden de diskettes die in 1970 op de markt kwamen?",
                keuze1 = "12 inch",
                keuze2 = "3.25 inch",
                keuze3 = "45 inch",
                antwoord = "8 inch"
            ),
            Question(
                3, 2,
                vraag = "Hoe heet de hoofdstad van Australië?",
                keuze1 = "Melbourne",
                keuze2 = "Sydney",
                keuze3 = "Brisbane",
                antwoord = "Canberra"
            ),
            Question(
                4, 2,
                vraag = "Waar ligt Narvik?",
                keuze1 = "Denemarken",
                keuze2 = "Finland",
                keuze3 = "Zweden",
                antwoord = "Noorwegen"
            )
        )
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                questionList.map { it }
                    .map {
                        questionDao.insert(it)
                    }
            }
        }
    }
}