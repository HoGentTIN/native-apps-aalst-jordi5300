package com.example.quizzit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizzit.domain.Question

@Dao
interface QuestionDao{
    @Query("SELECT * from question_table")
    fun getAllQuestions(): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)
}