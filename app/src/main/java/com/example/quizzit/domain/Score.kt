package com.example.quizzit.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "score_table")
data class Score(

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,

    @Json(name = "quizId")
    val plaats: Int,

    @Json(name = "nicknaam")
    val nicknaam: String,

    @Json(name = "punten")
    val punten: String,

    @Json(name = "tijd")
    val tijd: String
)
