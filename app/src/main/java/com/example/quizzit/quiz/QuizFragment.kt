package com.example.quizzit.quiz

import androidx.navigation.findNavController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.quizzit.R
import com.example.quizzit.R.layout.fragment_quizspelen
import com.example.quizzit.databinding.FragmentQuizspelenBinding

class QuizFragment : Fragment(),View.OnClickListener {

    private lateinit var quizViewModel : QuizViewModel;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentQuizspelenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_quizspelen, container, false)
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java);
        binding.quizViewModel = quizViewModel
        binding.keuze1Button.setOnClickListener(this)
        binding.keuze2Button.setOnClickListener(this)
        binding.keuze3Button.setOnClickListener(this)
        binding.keuze4Button.setOnClickListener(this)
        (activity as AppCompatActivity).supportActionBar?.title = "vraag " + quizViewModel.positieVraag.value!!.plus(1) + " van " + quizViewModel.lengteQuiz.value!!.minus(1)
        binding.setLifecycleOwner(this)
        return binding.root
    }
    override fun onClick(v:View){
        val b = v as Button
        val buttonText = b.getText().toString()
        quizViewModel.volgendeVraag(buttonText)
        (activity as AppCompatActivity).supportActionBar?.title = "vraag " + quizViewModel.positieVraag.value!!.plus(1) + " van " + quizViewModel.lengteQuiz.value!!.minus(1)
    }
}