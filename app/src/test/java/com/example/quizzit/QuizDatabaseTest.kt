package com.example.quizzit

import android.net.ConnectivityManager
import com.example.quizzit.database.QuestionDao
import com.example.quizzit.database.QuizDao
import com.example.quizzit.database.ScoreDao
import com.example.quizzit.domain.Question
import com.example.quizzit.domain.Quiz
import com.example.quizzit.domain.QuizRepository
import com.example.quizzit.domain.Score
import com.example.quizzit.domain.ScoreRepository
import com.example.quizzit.network.QuizApiService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class QuizDatabaseTest {

    private val quizDao: QuizDao = mockk()
    private val questionDao: QuestionDao = mockk()
    private val scoreDao: ScoreDao = mockk()
    private var quizRepository: QuizRepository = mockk()
    private var scoreRepository: ScoreRepository = mockk()
    private var quizApiService: QuizApiService = mockk()
    private var connectivityManager: ConnectivityManager = mockk()

    private val quiz1: Quiz = mockk()
    private val quizzes: List<Quiz> = mockk()
    private val quizId: Int = 0
    private val questions: List<Question> = mockk()
    private val scores: List<Score> = mockk()

    @Before
    fun createDb() {
        quizRepository = QuizRepository(quizDao, questionDao, quizApiService, connectivityManager)
        scoreRepository = ScoreRepository(scoreDao, quizApiService, connectivityManager)
        coEvery { connectivityManager.activeNetworkInfo.isConnected() } returns false
        coEvery { quizRepository.getAllQuizzes() } returns quizzes
        coEvery { quizRepository.getAllQuestions(quiz1) } returns questions
        coEvery { questionDao.getQuestionsFromQuiz(quizId) } returns questions
        coEvery { quiz1.id } returns quizId
        coEvery { scoreRepository.getTopScores(quizId) } returns scores
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun quizRepository_GetQuizzes_ReturnsQuizzes() {
        runBlocking {
            val quizzez = quizRepository.getAllQuizzes()
            assertEquals(quizzes, quizzez)
            coVerify { quizDao.getQuizzes() }
        }
    }

    @Test
    fun quizRepository_GetQuestionsFromQuiz_ReturnsQuestions() {
        runBlocking {
            val questionz = quizRepository.getAllQuestions(quiz1)
            assertEquals(questions, questionz)
            coVerify { questionDao.getQuestionsFromQuiz(quizId) }
        }
    }

    @Test
    fun scoreRepository_GetScoresFromQuiz_ReturnsScores() {
        runBlocking {
            val scorez = scoreRepository.getTopScores(quizId)
            assertEquals(scores, scorez)
            coVerify { scoreDao.getScores(quizId) }
        }
    }
}