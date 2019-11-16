package com.example.quizzit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quizzit.domain.Question
import com.example.quizzit.domain.Quiz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [Quiz::class,Question::class], version = 3, exportSchema = false)
@TypeConverters(QuestionConverter::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract val quizDao: QuizDao
    abstract val questionDao : QuestionDao

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
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

    private fun putQuizzes() {
        val quizList = listOf(
            Quiz(1, "quiz1", "algemeen")
            ,
            Quiz(
                2,"quiz1", "algemeen")
        )

        val questionList = listOf(
                Question(1,1,
                    vraag = "Wanneer werd John F. Kennedy vermoord?",
                    keuze1 = "1961",
                    keuze2 = "1965",
                    keuze3 = "1967",
                    antwoord = "1963"
                ),
                Question(2,1,
                    vraag = "Welke diameter hadden de diskettes die in 1970 op de markt kwamen?",
                    keuze1 = "12 inch",
                    keuze2 = "3.25 inch",
                    keuze3 = "45 inch",
                    antwoord = "8 inch"
                ),
                Question(3,1,
                    vraag = "Waar ligt Narvik?",
                    keuze1 = "Denemarken",
                    keuze2 = "Finland",
                    keuze3 = "Zweden",
                    antwoord = "Noorwegen"
                ),
                Question(4,2,
                    vraag = "Welke diameter hadden de diskettes die in 1970 op de markt kwamen?",
                    keuze1 = "12 inch",
                    keuze2 = "3.25 inch",
                    keuze3 = "45 inch",
                    antwoord = "8 inch"
                ),
                Question(5,2,
                    vraag = "Waar ligt Narvik?",
                    keuze1 = "Denemarken",
                    keuze2 = "Finland",
                    keuze3 = "Zweden",
                    antwoord = "Noorwegen"
                )
            )



        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                quizList.map { it }
                    .map { quizDao.insert(it)}
            }
            questionList.map { it }
                .map { questionDao.insert(it)}
        }
        }
    }
