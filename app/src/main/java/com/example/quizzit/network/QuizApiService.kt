package com.example.quizzit.network

import com.example.quizzit.domain.Question
import com.example.quizzit.domain.Quiz
import com.example.quizzit.domain.Score
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:5000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

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

object QuizApi {
    val retrofitService: QuizApiService by lazy {
        retrofit.create(QuizApiService::class.java)
    }
}
