package com.example.quizzit.domain

data class Quiz(val naam: String,
                    val categorie: String,val questions:  MutableList<Question> = arrayListOf());