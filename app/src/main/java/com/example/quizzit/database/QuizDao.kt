package com.example.quizzit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizzit.domain.Quiz

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quiz: Quiz)

    @Query("SELECT * FROM quiz_table")
    fun getQuizzes(): List<Quiz>
}