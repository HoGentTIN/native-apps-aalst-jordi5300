package com.example.quizzit.quiz

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.quizzit.R.layout.fragment_quizspelen
import com.example.quizzit.database.QuizDatabase
import com.example.quizzit.databinding.FragmentQuizspelenBinding
import com.example.quizzit.domain.QuizRepository
import com.example.quizzit.network.QuizApi


class QuizFragment : Fragment(), View.OnClickListener {

    private lateinit var quizViewModel: QuizViewModel
    private lateinit var binding: FragmentQuizspelenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, fragment_quizspelen, container, false
        )

        val quizApiService = QuizApi.retrofitService
        val quizDao = QuizDatabase.getInstance(requireContext()).quizDao
        val questionDao = QuizDatabase.getInstance(requireContext()).questionDao
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val viewModelFactory = QuizViewModelFactory(
            QuizRepository(
                quizDao,
                questionDao,
                quizApiService,
                connectivityManager
            )
        )
        quizViewModel = ViewModelProviders.of(this, viewModelFactory).get(QuizViewModel::class.java)
        binding.quizViewModel = quizViewModel
        binding.btnKeuze1.setOnClickListener(this)
        binding.btnKeuze2.setOnClickListener(this)
        binding.btnKeuze3.setOnClickListener(this)
        binding.btnKeuze4.setOnClickListener(this)
        binding.lifecycleOwner = this

        if (savedInstanceState == null) {
            binding.viewTimer.isCountDown = false
            binding.viewTimer.base = SystemClock.elapsedRealtime() + 2000
            Thread.sleep(2_000)
            binding.viewTimer.start()
        } else {
            val timer = savedInstanceState.getString("timer")
            binding.viewTimer.isCountDown = false
            binding.viewTimer.base = SystemClock.elapsedRealtime() - timer!!.toLong()
            binding.viewTimer.start()
        }

        quizViewModel.lengteQuiz.observe(this, Observer { lengte ->
            (activity as AppCompatActivity).supportActionBar?.title =
                "vraag 1 van $lengte"
        })
        return binding.root
    }

    override fun onClick(v: View) {
        val b = v as Button
        val buttonText = b.getText().toString()
        quizViewModel.volgendeVraag(buttonText)
        if (quizViewModel.einde) {
            val action = QuizFragmentDirections.actionQuizFragmentToScoreFragment(
                quizViewModel.score.value!!.toInt(),
                quizViewModel.lengteQuiz.value!!.toInt(),
                binding.viewTimer.text.toString(),
                quizViewModel.quiz.id
            )
            this.findNavController().navigate(action)
        }
        (activity as AppCompatActivity).supportActionBar?.title =
            "vraag " + this.quizViewModel.positieVraagTitel.value + " van " + quizViewModel.lengteQuiz.value
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // Save timer value in saveInstanceState bundle
        val time = SystemClock.elapsedRealtime() - binding.viewTimer.base
        savedInstanceState.putString("timer", time.toString())
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onPause() {
        binding.viewTimer.stop()
        super.onPause()
    }

    override fun onResume() {
        binding.viewTimer.start()
        super.onResume()
    }
}