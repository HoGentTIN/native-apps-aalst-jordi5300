package com.example.quizzit.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzit.domain.Question
import com.example.quizzit.domain.Quiz
import com.example.quizzit.domain.QuizRepository
import java.util.Timer
import java.util.TimerTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizViewModel(private val quizRepository: QuizRepository) : ViewModel() {

    var quiz = Quiz(1, "", "")

    private var questions = listOf<Question>()

    var einde = false

    val score = MutableLiveData<Int>()

    val positieVraag = MutableLiveData<Int>()

    val positieVraagTitel = MutableLiveData<Int>()

    val lengteQuiz = MutableLiveData<Int>()

    val vraag = MutableLiveData<String>()

    val keuze1 = MutableLiveData<String>()

    val keuze2 = MutableLiveData<String>()

    val keuze3 = MutableLiveData<String>()

    val keuze4 = MutableLiveData<String>()

    val antwoord = MutableLiveData<String>()

    var mElapsedTime = MutableLiveData<Long>()

    var timer: Timer? = null

    init {
        Thread.sleep(1000)
        positieVraag.value = 0
        positieVraagTitel.value = 0
        score.value = 0
        lengteQuiz.value = 0
        mElapsedTime.value = 0
        viewModelScope.launch {
            resetQuizzes()
            randomizeQuestionsAndSetQuestion()
            val timer = Timer()
            val task = object : TimerTask() {
                override fun run() {
                    mElapsedTime.postValue(mElapsedTime.value!! + 1)
                }
            }
            timer.schedule(task, 0, 1000)
        }
    }

    suspend fun resetQuizzes() {
        withContext(Dispatchers.Default) {
            quiz = quizRepository.getAllQuizzes().shuffled().first()
            questions = quizRepository.getAllQuestions(quiz).shuffled()
        }
    }

    fun randomizeQuestionsAndSetQuestion() {
        antwoord.value = questions[positieVraag.value!!.toInt()].antwoord
        var huidigeVraag = questions[positieVraag.value!!.toInt()]
        val keuzes = mutableListOf(
            huidigeVraag.keuze1,
            huidigeVraag.keuze2,
            huidigeVraag.keuze3,
            huidigeVraag.antwoord
        )
        keuzes.shuffle()
        keuze1.value = keuzes.elementAt(0)
        keuze2.value = keuzes.elementAt(1)
        keuze3.value = keuzes.elementAt(2)
        keuze4.value = keuzes.elementAt(3)
        vraag.value = questions[positieVraag.value!!.toInt()].vraag
        lengteQuiz.value = questions.size.plus(1)
    }

    fun volgendeVraag(text: String) {
        if (text.equals(this.antwoord.value)) {
            this.score.value = score.value?.inc()
        }
        positieVraag.value = positieVraag.value?.inc()
        if (positieVraag.value!!.toInt().plus(1) >= lengteQuiz.value!!.toInt()) {
            einde = true
        } else {
            positieVraagTitel.value = positieVraag.value?.plus(1)
            randomizeQuestionsAndSetQuestion()
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}
