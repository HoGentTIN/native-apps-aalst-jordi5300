package com.example.quizzit.score

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizzit.databinding.FragmentScoreBinding
import com.example.quizzit.R.layout.fragment_score
import com.example.quizzit.database.QuizDatabase
import com.example.quizzit.domain.ScoreRepository
import com.example.quizzit.network.QuizApi

class ScoreFragment : Fragment() {

    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
            inflater, fragment_score, container, false
        )
        (activity as AppCompatActivity).supportActionBar?.title = "Scores"
        binding.setLifecycleOwner(this)
        val scoreDao = QuizDatabase.getInstance(requireContext()).scoreDao
        val quizApiService = QuizApi.retrofitService
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val viewModelFactory = ScoreViewModelFactory(
            ScoreRepository(
                scoreDao,
                quizApiService,
                connectivityManager
            ), ScoreFragmentArgs.fromBundle(arguments!!).quizId
        )
        val scoreAdapter = ScoreAdapter()
        scoreViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = scoreViewModel
        val score = ScoreFragmentArgs.fromBundle(arguments!!).score
        val aantal = ScoreFragmentArgs.fromBundle(arguments!!).aantalVragen
        val tijd = ScoreFragmentArgs.fromBundle(arguments!!).tijd
        val quizId = ScoreFragmentArgs.fromBundle(arguments!!).quizId
        binding.txtScore.setText("Uw score op de quiz is: $score op: $aantal in $tijd minuten")
        binding.txtNicknaam.setText("")
        binding.txtNicknaam.setHint("Nicknaam")
        scoreViewModel.scores.observe(viewLifecycleOwner, Observer {
            it?.let {
                scoreAdapter.setList(it)
            }
        })
        binding.recyclerScoreResScores?.adapter = scoreAdapter
        val manager = GridLayoutManager(activity, 1)
        binding.recyclerScoreResScores?.layoutManager = manager
        binding.btnSubmitscore.setOnClickListener { view ->
            val nicknaam = binding.txtNicknaam.text
            if (nicknaam!!.isEmpty()) {
            //snackbar vul nicknaam in om de score te posten aub
            } else {
                val result = scoreViewModel.postScore(quizId, nicknaam.toString(), score, tijd)
                if (!result) {
                    //snackbar nog maken met er is internet nodig om post online te zetten
                }
            }
        }
        return binding.root
    }
}