package com.example.quizzit.modules

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import com.example.quizzit.activities.LoadingViewModel
import com.example.quizzit.database.QuestionDao
import com.example.quizzit.database.QuizDao
import com.example.quizzit.database.QuizDatabase
import com.example.quizzit.database.ScoreDao
import com.example.quizzit.domain.QuizRepository
import com.example.quizzit.domain.ScoreRepository
import com.example.quizzit.network.BASE_URL
import com.example.quizzit.network.QuizApiService
import com.example.quizzit.screens.quiz.selecteren.QuizSelecterenViewModel
import com.example.quizzit.screens.quiz.spelen.QuizSpelenViewModel
import com.example.quizzit.screens.score.ScoreViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val mainModule = module {

    viewModel { QuizSelecterenViewModel(get()) }
    viewModel { QuizSpelenViewModel(get()) }
    viewModel { LoadingViewModel(get(), get()) }
    viewModel { (id: Int) -> ScoreViewModel(get(), id) }

    single { provideQuizDao(get()) }
    single { provideQuestionDao(get()) }
    single { provideScoreDao(get()) }

    single { QuizRepository(get(), get(), get(), get()) }
    single { ScoreRepository(get(), get(), get()) }

    single { provideMoshi() }
    single { provideRetrofit(get()) }
    single { provideQuizApiService(get()) }
    single { provideSharedPreferences(androidContext()) }
    single { provideConnectivityManager(androidContext()) }
    single { QuizDatabase.getInstance(androidContext()) }
    single { provideSharedPreferencesEditor(androidContext()) }
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

fun provideRetrofit(moshi: Moshi): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()
}

fun provideSharedPreferences(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

fun provideSharedPreferencesEditor(context: Context): SharedPreferences.Editor {
    return PreferenceManager.getDefaultSharedPreferences(context).edit()
}

fun provideQuizApiService(retrofit: Retrofit): QuizApiService {
    return retrofit.create(QuizApiService::class.java)
}

fun provideConnectivityManager(context: Context): ConnectivityManager {
    return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}

fun provideQuizDao(database: QuizDatabase): QuizDao {
    return database.quizDao
}

fun provideQuestionDao(database: QuizDatabase): QuestionDao {
    return database.questionDao
}

fun provideScoreDao(database: QuizDatabase): ScoreDao {
    return database.scoreDao
}
