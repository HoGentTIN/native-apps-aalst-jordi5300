package com.example.quizzit.domain

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "score_table",
    indices = [(Index(value = ["id"], unique = true)), (Index(value = ["quizID"], unique = false))],
    foreignKeys = [ForeignKey(
        entity = Quiz::class,
        parentColumns = ["id"],
        childColumns = ["quizID"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class Score(

    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    var id: Int = 0,

    @Json(name = "quizId")
    val quizID: Int,

    @Json(name = "nicknaam")
    val nicknaam: String,

    @Json(name = "punten")
    val punten: Int,

    @Json(name = "tijd")
    val tijd: String
)
