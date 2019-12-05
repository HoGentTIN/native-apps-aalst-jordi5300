package com.example.quizzit.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizzit.domain.Score

@Dao
interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: Score)

    @Query("SELECT * FROM score_table WHERE id=:scoreId")
    suspend  fun getScores(scoreId: Int): List<Score>
}