package com.example.quizzit.domain

import androidx.room.*
import com.squareup.moshi.Json

@Entity(tableName = "quiz_table")
data class Quiz(

    @Json(name = "naam")
    val naam: String,
    @Json(name = "categorie")
    val categorie: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L)

