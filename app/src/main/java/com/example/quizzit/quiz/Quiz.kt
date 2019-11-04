package com.example.quizzit.quiz

data class Quiz(val naam: String,
                    val categorie: String,val questions:  MutableList<Question> = arrayListOf());