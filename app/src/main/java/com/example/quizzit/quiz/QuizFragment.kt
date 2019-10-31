package com.example.quizzit.quiz

import androidx.navigation.findNavController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.quizzit.R
import com.example.quizzit.R.id.quizFragment

class QuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(R.layout.fragment_quizspelen,
            container, false)
    }
}