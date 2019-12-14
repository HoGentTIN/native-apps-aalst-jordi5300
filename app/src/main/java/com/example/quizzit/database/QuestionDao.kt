package com.example.quizzit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizzit.domain.Question

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: Question)

    @Query("SELECT * FROM question_table WHERE quizID=:quizId")
    suspend fun getQuestionsFromQuiz(quizId: Int): List<Question>
}
