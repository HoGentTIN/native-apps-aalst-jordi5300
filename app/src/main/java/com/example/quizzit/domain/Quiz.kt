package com.example.quizzit.domain

import androidx.room.*
import com.example.quizzit.database.QuestionConverter
import com.example.quizzit.database.QuizConverter

@Entity(tableName = "quiz_table")
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val naam: String,
    val categorie: String)

