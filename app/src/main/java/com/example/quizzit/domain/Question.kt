package com.example.quizzit.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "question_table",
    indices = [(Index(value = ["id"], unique = true)), (Index(value = ["quizID"], unique = false))],
    foreignKeys = [ForeignKey(
        entity = Quiz::class,
        parentColumns = ["id"],
        childColumns = ["quizID"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class Question(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @Json(name = "questionId")
    val quizID: Int,

    @Json(name = "vraag")
    val vraag: String,

    @Json(name = "keuze1")
    val keuze1: String,

    @Json(name = "keuze2")
    val keuze2: String,

    @Json(name = "keuze3")
    val keuze3: String,

    @Json(name = "antwoord")
    val antwoord: String
)
