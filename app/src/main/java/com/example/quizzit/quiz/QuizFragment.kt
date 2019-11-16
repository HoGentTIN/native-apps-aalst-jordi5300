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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizzit.R
import com.example.quizzit.R.layout.fragment_quizspelen
import com.example.quizzit.database.QuizDao
import com.example.quizzit.database.QuizDatabase
import com.example.quizzit.databinding.FragmentQuizspelenBinding
import com.example.quizzit.domain.QuizRepository

class QuizFragment : Fragment(),View.OnClickListener {

    private lateinit var quizViewModel: QuizViewModel;
    private lateinit var binding: FragmentQuizspelenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, fragment_quizspelen, container, false
        )
        val quizDao = QuizDatabase.getInstance(requireContext()).quizDao
        val questionDao = QuizDatabase.getInstance(requireContext()).questionDao
        val viewModelFactory = QuizViewModelFactory(QuizRepository(quizDao,questionDao))
        quizViewModel = ViewModelProviders.of(this, viewModelFactory).get(QuizViewModel::class.java)
        binding.quizViewModel = quizViewModel
        binding.keuze1Button.setOnClickListener(this)
        binding.keuze2Button.setOnClickListener(this)
        binding.keuze3Button.setOnClickListener(this)
        binding.keuze4Button.setOnClickListener(this)
        binding.lifecycleOwner = this
        //(activity as AppCompatActivity).supportActionBar?.title =
        //    "vraag " + quizViewModel.positieVraag.value!!.plus(1) + " van " + quizViewModel.lengteQuiz.value!!.minus(
        //        1
         //   )
        return binding.root
    }

    override fun onClick(v: View) {
        val b = v as Button
        val buttonText = b.getText().toString()
        quizViewModel.volgendeVraag(buttonText)
        if (quizViewModel.einde.equals(true)) {
            val action = QuizFragmentDirections.actionQuizFragmentToScoreFragment(quizViewModel.score.value!!.toInt())
            this.findNavController().navigate(action)
        }
        (activity as AppCompatActivity).supportActionBar?.title =
            "vraag " + quizViewModel.positieVraag.value!!.plus(1) + " van " + quizViewModel.lengteQuiz.value!!.minus(
                1
            )
    }
}