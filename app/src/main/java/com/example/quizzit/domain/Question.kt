package com.example.quizzit.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "question_table",
    indices = [(Index(value = ["id"], unique = true)), (Index(value = ["quizID"], unique = false))],
    foreignKeys = [ForeignKey(
        entity = Quiz::class,
        parentColumns = ["id"],
        childColumns = ["quizID"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)])
data class Question(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val quizID: Long = 0L,
    val vraag: String,
    val keuze1: String,
    val keuze2: String,
    val keuze3: String,
    val antwoord: String)