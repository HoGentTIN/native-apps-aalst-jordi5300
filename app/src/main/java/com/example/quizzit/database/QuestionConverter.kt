package com.example.quizzit.database


import androidx.room.TypeConverter
import com.example.quizzit.domain.Question
import com.google.gson.Gson

class QuestionConverter {

    @TypeConverter
    fun listToJson(value: List<Question>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Question>? {
        val objects = Gson().fromJson(value, Array<Question>::class.java) as Array<Question>
        val list = objects.toList()
        return list
    }
}