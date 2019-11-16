package com.example.quizzit.database

import androidx.room.TypeConverter
import com.example.quizzit.domain.Quiz
import com.google.gson.Gson

class QuizConverter {
    @TypeConverter
    fun listToJson(value: List<Quiz>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Quiz>? {
        val objects = Gson().fromJson(value, Array<Quiz>::class.java) as List<Quiz>
        return objects
    }
}