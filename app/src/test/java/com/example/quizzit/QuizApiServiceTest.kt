package com.example.quizzit

import android.net.ConnectivityManager
import android.net.NetworkInfo
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
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class QuizApiServiceTest {
    private val quizDao: QuizDao = mockk()
    private val questionDao: QuestionDao = mockk()
    private val scoreDao: ScoreDao = mockk()
    private lateinit var quizRepository: QuizRepository
    private lateinit var scoreRepository: ScoreRepository

    private val quiz1: Quiz = mockk()
    private val quiz2: Quiz = mockk()
    private val score1: Score = mockk()
    private val score2: Score = mockk()
    private val questions: List<Question> = mockk()
    private val quizId: Int = 0

    private val quizApiService: QuizApiService = mockk()
    private val connectivityManager: ConnectivityManager = mockk()
    private val networkInfo: NetworkInfo = mockk()

    @Before
    fun setUp() {
        coEvery { quizDao.getQuizzes() } returns listOf(quiz1, quiz2)
        coEvery { questionDao.getQuestionsFromQuiz(quizId) } returns questions
        coEvery { scoreDao.getScores(quizId) } returns arrayListOf(score1, score2)

        coEvery { quizApiService.getQuizzes() } returns arrayListOf(quiz1, quiz2)
        coEvery { quizApiService.getQuestions(quizId) } returns questions
        coEvery { quizApiService.getScores(quizId) } returns arrayListOf(score1, score2)
        coEvery { quizDao.insert(any()) } returns Unit
        coEvery { questionDao.insert(any()) } returns Unit
        coEvery { scoreDao.insert(any()) } returns Unit
        every { connectivityManager.activeNetworkInfo } returns networkInfo
        quizRepository = QuizRepository(quizDao, questionDao, quizApiService, connectivityManager)
        scoreRepository = ScoreRepository(scoreDao, quizApiService, connectivityManager)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun quizRepository_GetQuizzesOnline_ReturnsFromApi() {
        // Arrange
        every { networkInfo.isConnected } returns true
        // Act
        runBlockingTest {
            val quizzes = quizRepository.getAllQuizzes()
            // Assert
            coVerify { quizApiService.getQuizzes() }
            assertEquals(2, quizzes.size)
        }
    }

    @Test
    fun quizRepository_GetQuizzesOffline_ReturnsFromDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns false
        // Act
        runBlockingTest {
            val quizzes = quizRepository.getAllQuizzes()
            // Assert
            assertEquals(2, quizzes.size)
        }
    }

    @Test
    fun scoreRepository_GetScoresOnline_ReturnsFromApi() {
        // Arrange
        every { networkInfo.isConnected } returns true
        // Act
        runBlockingTest {
            val scorez = scoreRepository.getTopScores(quizId)
            // Assert
            coVerify { quizApiService.getScores(quizId) }
            assertEquals(2, scorez.size)
        }
    }

    @Test
    fun scoreRepository_GetScoresOffline_ReturnsFromDatabase() {
        // Arrange
        every { networkInfo.isConnected } returns false
        // Act
        runBlockingTest {
            val scorez = scoreRepository.getTopScores(quizId)
            // Assert
            assertEquals(2, scorez.size)
        }
    }
}
