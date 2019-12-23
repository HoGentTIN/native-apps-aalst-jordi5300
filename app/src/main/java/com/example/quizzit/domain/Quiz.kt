package com.example.quizzit.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "quiz_table")
data class Quiz(

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,

    @Json(name = "naam")
    val naam: String,

    @Json(name = "categorie")
    val categorie: String
)
