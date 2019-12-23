package com.example.quizzit.quiz.selecteren

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzit.R
import com.example.quizzit.database.QuizDatabase
import com.example.quizzit.databinding.FragmentQuizsSelecterenBinding
import com.example.quizzit.domain.QuizRepository
import com.example.quizzit.network.QuizApi

class QuizSelecterenFragment : Fragment() {

    private lateinit var quizSelecterenViewModel: QuizSelecterenViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentQuizsSelecterenBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_quizs_selecteren, container, false
        )
        val quizApiService = QuizApi.retrofitService
        val quizDao = QuizDatabase.getInstance(requireContext()).quizDao
        val questionDao = QuizDatabase.getInstance(requireContext()).questionDao
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val viewModelFactory =
            QuizSelecterenViewModelFactory(
                QuizRepository(
                    quizDao,
                    questionDao,
                    quizApiService,
                    connectivityManager
                )
            )
        quizSelecterenViewModel = ViewModelProviders.of(this, viewModelFactory).get(QuizSelecterenViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.title = "Quiz selecteren"
        binding.setLifecycleOwner(this)

        val quizAdapter = QuizAdapter(QuizListener { quizId ->
            val action = QuizSelecterenFragmentDirections.actionQuizSelecterenFragmentToQuizFragment(quizId)
            this.findNavController().navigate(action)
        })

        quizSelecterenViewModel.quizzes.observe(viewLifecycleOwner, Observer {
            it?.let {
                quizAdapter.setList(it)
            }
        })
        binding.recyclerQuizResQuizzes?.adapter = quizAdapter
        val manager = LinearLayoutManager(context)
        binding.recyclerQuizResQuizzes?.layoutManager = manager
        binding.txtSelecteerQuiz.text = "Selecteer een van de volgende quizzes: "
        return binding.root
    }
}
