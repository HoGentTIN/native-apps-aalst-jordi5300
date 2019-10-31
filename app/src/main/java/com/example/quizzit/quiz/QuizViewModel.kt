package com.example.quizzit.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){
    private var huidigeVraag =  Question(vraag = "vraag?",
        keuze1 ="keuze", keuze2="keuze2", keuze3="keuze3", antwoord ="antwoord")
private var positieVraag = 0
    private var score = 0
    private var lengteQuiz = 0
    private var vraag = "vraag"
    private var keuze1 = "keuze1"
    private var keuze2 = "keuze2"
    private var keuze3 = "keuze3"
    private var keuze4 = "keuze4"
    private var antwoord = "antwoord"
    private var sourceArray = {}

    private val vragen: MutableList<Question> = mutableListOf(Question(vraag = "Wanneer werd John F. Kennedy vermoord?", keuze1 ="1961", keuze2="1965", keuze3="1967", antwoord ="1963"),
    Question(vraag = "Welke diameter hadden de diskettes die in 1970 op de markt kwamen?",
    keuze1 ="12 inch", keuze2="3.25 inch", keuze3="45 inch", antwoord ="8 inch"),
    Question(vraag = "Waar ligt Narvik?",
    keuze1 ="Denemarken", keuze2="Finland", keuze3="Zweden", antwoord ="Noorwegen")
    )
    init {
        positieVraag = 0
        score = 0
        lengteQuiz = vragen.size+1
        randomizeQuestions()
    }
    private fun randomizeQuestions() {
        setMeerkeuzevraag()
    }
    private fun setMeerkeuzevraag() {

        antwoord = vragen[positieVraag].antwoord
      // huidigeVraag  = this.shuffleVraag(vragen[positieVraag])
        vraag = huidigeVraag.vraag
        keuze1 = huidigeVraag.keuze1
        keuze2 = huidigeVraag.keuze2
        keuze3 = huidigeVraag.keuze3
        keuze4 = huidigeVraag.antwoord

    }
    /*
  fun shuffleVraag(obj:Question) {

      var vraag = obj.vraag;
       sourceArray = {obj.keuze1;obj.keuze2;obj.keuze3;obj.antwoord}
      for (index in sourceArray) {
          var j = index + Math.floor(Math.random() * (sourceArray.length - i));
          var temp = sourceArray[j];
          sourceArray[j] = sourceArray[index];
          sourceArray[index] = temp;
      }
      return new Quiz(vraag,sourceArray[0],sourceArray[1],sourceArray[2],sourceArray[3]);
  }
  */

private fun volgendeVraag(){
    positieVraag++;
    if(positieVraag >= lengteQuiz){
        // ga naar highscores
    }
}
}