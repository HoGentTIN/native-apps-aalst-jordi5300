package com.example.quizzit.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizzit.R
import com.example.quizzit.R.layout.fragment_score
import com.example.quizzit.databinding.FragmentScoreBinding
import com.example.quizzit.modules.provideConnectivityManager
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ScoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var arg = ScoreFragmentArgs.fromBundle(arguments!!)
        val scoreViewModel: ScoreViewModel by viewModel { parametersOf(arg.quizId) }
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
            inflater, fragment_score, container, false
        )
        (activity as AppCompatActivity).supportActionBar?.title = "Scores"
        binding.setLifecycleOwner(this)
        val scoreAdapter = ScoreAdapter()
        val score = ScoreFragmentArgs.fromBundle(arguments!!).score
        val aantal = ScoreFragmentArgs.fromBundle(arguments!!).aantalVragen
        val tijd = ScoreFragmentArgs.fromBundle(arguments!!).tijd
        val quizId = ScoreFragmentArgs.fromBundle(arguments!!).quizId
        binding.txtScore.setText("Uw score op de quiz is: $score op: $aantal in $tijd seconden")
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
                val snackbar = Snackbar.make(
                    view,
                    "Vul een naam in om de score te posten aub!",
                    Snackbar.LENGTH_LONG
                )
                snackbar.show()
            } else {
                if(scoreViewModel.connectedToInternet(provideConnectivityManager(requireContext()))) {
                    scoreViewModel.postScore(quizId, nicknaam.toString(), score, tijd)
                    binding.btnSubmitscore?.isEnabled = false
                    binding.btnSubmitscore?.setTextColor(R.color.blackQuizzit.toInt())
                    binding.btnSubmitscore?.setBackgroundColor(R.color.blackQuizzit.toInt())
                    val snackbar = Snackbar.make(
                        view,
                        "Score staat nu online!",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.show()
                }
                else{
                    val snackbar2 = Snackbar.make(
                        view,
                        "Er is internet nodig om de score online te zetten!",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar2.show()
                }
            }
        }
        return binding.root
    }
}
