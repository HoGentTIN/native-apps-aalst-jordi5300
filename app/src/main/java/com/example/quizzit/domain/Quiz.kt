package com.example.quizzit.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.quizzit.database.QuestionConverter
import com.example.quizzit.database.QuizConverter

@Entity(tableName = "quiz_table")
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val naam: String,
    val categorie: String,
    @TypeConverters(QuestionConverter::class)
    val questions:  List<Question>)
