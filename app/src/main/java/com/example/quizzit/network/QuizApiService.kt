package com.example.quizzit.network

import com.example.quizzit.domain.Question
import com.example.quizzit.domain.Quiz
import com.example.quizzit.domain.Score
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val BASE_URL = "http://10.0.2.2:5000/"

interface QuizApiService {

    @GET("api/Quiz")
    suspend fun getQuizzes(): List<Quiz>

    @GET("api/Quiz/{id}/Meerkeuzevragen")
    suspend fun getQuestions(@Path("id") id: Int): List<Question>

    @GET("api/Quiz/{id}/Scores")
    suspend fun getScores(@Path("id") id: Int): List<Score>

    @POST("api/Quiz/Score")
    suspend fun postScore(@Body score: Score)
}
