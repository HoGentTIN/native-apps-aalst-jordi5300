package com.example.quizzit.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
//private val quizRepository : QuizRepository

    private var quiz = Quiz("", "", mutableListOf())

    val score: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val positieVraag: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val lengteQuiz: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val vraag: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val keuze1: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val keuze2: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val keuze3: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val keuze4: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val antwoord: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val quizzes: MutableList<Quiz> = mutableListOf(
        Quiz(
            "quiz1", "algemeen", mutableListOf(
                Question(
                    vraag = "Wanneer werd John F. Kennedy vermoord?",
                    keuze1 = "1961",
                    keuze2 = "1965",
                    keuze3 = "1967",
                    antwoord = "1963"
                ),
                Question(
                    vraag = "Welke diameter hadden de diskettes die in 1970 op de markt kwamen?",
                    keuze1 = "12 inch",
                    keuze2 = "3.25 inch",
                    keuze3 = "45 inch",
                    antwoord = "8 inch"
                ),
                Question(
                    vraag = "Waar ligt Narvik?",
                    keuze1 = "Denemarken",
                    keuze2 = "Finland",
                    keuze3 = "Zweden",
                    antwoord = "Noorwegen"
                )
            )
        ),
        Quiz(
            "quiz1", "algemeen", mutableListOf(
                Question(
                    vraag = "Welke diameter hadden de diskettes die in 1970 op de markt kwamen?",
                    keuze1 = "12 inch",
                    keuze2 = "3.25 inch",
                    keuze3 = "45 inch",
                    antwoord = "8 inch"
                ),
                Question(
                    vraag = "Waar ligt Narvik?",
                    keuze1 = "Denemarken",
                    keuze2 = "Finland",
                    keuze3 = "Zweden",
                    antwoord = "Noorwegen"
                )
            )
        )
    )

    init {
        positieVraag.value = 0
        score.value = 0
        quiz = quizzes.shuffled().take(1).get(0)
        lengteQuiz.value = quiz.questions.size + 1
        randomizeQuestionsAndSetQuestion()
    }

    public fun randomizeQuestionsAndSetQuestion() {
        antwoord.value = quiz.questions[positieVraag.value!!.toInt()].antwoord
        var huidigeVraag = quiz.questions[positieVraag.value!!.toInt()]
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
        vraag.value = quiz.questions[positieVraag.value!!.toInt()].vraag
    }


    public fun volgendeVraag(text: String) {
        if(text.equals(this.antwoord.value)){
            this.score.value?.plus(1)
        }
        positieVraag.value = positieVraag.value?.inc()
        if (positieVraag.value!!.toInt() >= lengteQuiz.value!!.toInt()) {
            // ga naar highscores
        } else {
            randomizeQuestionsAndSetQuestion()
        }
    }
}