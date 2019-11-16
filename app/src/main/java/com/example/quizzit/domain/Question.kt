package com.example.quizzit.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val vraag: String,
    val keuze1: String,
    val keuze2: String,
    val keuze3: String,
    val antwoord: String);