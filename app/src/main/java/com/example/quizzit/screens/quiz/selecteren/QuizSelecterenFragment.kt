package com.example.quizzit.screens.quiz.selecteren

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzit.R
import com.example.quizzit.databinding.FragmentQuizsSelecterenBinding
import org.koin.android.viewmodel.ext.android.viewModel

class QuizSelecterenFragment : Fragment() {

    private val quizSelecterenViewModel: QuizSelecterenViewModel by viewModel()
    private lateinit var binding: FragmentQuizsSelecterenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quizs_selecteren, container, false)
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
