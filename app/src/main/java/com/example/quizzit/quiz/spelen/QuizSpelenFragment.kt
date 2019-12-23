package com.example.quizzit.quiz.spelen

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.quizzit.R
import com.example.quizzit.database.QuizDatabase
import com.example.quizzit.databinding.FragmentQuizspelenBinding
import com.example.quizzit.domain.QuizRepository
import com.example.quizzit.network.QuizApi

class QuizSpelenFragment : Fragment(), View.OnClickListener {

    private lateinit var quizSpelenViewModel: QuizSpelenViewModel
    private lateinit var binding: FragmentQuizspelenBinding
    private lateinit var savedInstance: Bundle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_quizspelen, container, false
        )
        savedInstance = Bundle()
        val quizApiService = QuizApi.retrofitService
        val quizDao = QuizDatabase.getInstance(requireContext()).quizDao
        val questionDao = QuizDatabase.getInstance(requireContext()).questionDao
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val viewModelFactory =
            QuizSpelenViewModelFactory(
                QuizRepository(
                    quizDao,
                    questionDao,
                    quizApiService,
                    connectivityManager
                )
            )
        quizSpelenViewModel = ViewModelProviders.of(this, viewModelFactory).get(QuizSpelenViewModel::class.java)
        binding.quizSpelenViewModel = quizSpelenViewModel
        binding.btnKeuze1.setOnClickListener(this)
        binding.btnKeuze2.setOnClickListener(this)
        binding.btnKeuze3.setOnClickListener(this)
        binding.btnKeuze4.setOnClickListener(this)
        quizSpelenViewModel.quiz.id = QuizSpelenFragmentArgs.fromBundle(arguments!!).quizId
        binding.lifecycleOwner = this

        quizSpelenViewModel.mElapsedTime.observe(this, Observer { l ->
            binding.viewTimer.text = l.toString()
        })

        quizSpelenViewModel.lengteQuiz.observe(this, Observer { lengte ->
            (activity as AppCompatActivity).supportActionBar?.title =
                "vraag 1 van $lengte"
        })
        return binding.root
    }

    override fun onClick(v: View) {
        val b = v as Button
        val buttonText = b.getText().toString()
        quizSpelenViewModel.volgendeVraag(buttonText)
        if (quizSpelenViewModel.einde) {
            val action =
                QuizSpelenFragmentDirections.actionQuizFragmentToScoreFragment(
                    quizSpelenViewModel.score.value!!.toInt(),
                    quizSpelenViewModel.lengteQuiz.value!!.toInt(),
                    binding.viewTimer.text.toString(),
                    quizSpelenViewModel.quiz.id
                )
            this.findNavController().navigate(action)
        }
        (activity as AppCompatActivity).supportActionBar?.title =
            "vraag " + this.quizSpelenViewModel.positieVraagTitel.value + " van " + quizSpelenViewModel.lengteQuiz.value
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        val time = quizSpelenViewModel.mElapsedTime
        savedInstanceState.putString("time", time.value.toString())
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onResume() {
        val time = savedInstance.getString("time")?.toLong()
        if (time != null) {
            quizSpelenViewModel.mElapsedTime.postValue(time)
        }
        quizSpelenViewModel.timer?.run { }
        super.onResume()
    }

    override fun onPause() {
        onSaveInstanceState(savedInstance)
        quizSpelenViewModel.timer?.cancel()
        super.onPause()
    }
}
