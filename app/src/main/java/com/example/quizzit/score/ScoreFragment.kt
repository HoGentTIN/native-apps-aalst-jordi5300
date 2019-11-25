package com.example.quizzit.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.quizzit.databinding.FragmentScoreBinding
import com.example.quizzit.R.layout.fragment_score
import kotlinx.android.synthetic.main.fragment_score.*

class ScoreFragment : Fragment(){

    private lateinit var scoreViewModel : ScoreViewModel;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
            inflater, fragment_score, container, false)
        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)
        binding.scoreViewModel = scoreViewModel
        (activity as AppCompatActivity).supportActionBar?.title = "Scores"
        binding.setLifecycleOwner(this)
        val score = ScoreFragmentArgs.fromBundle(arguments!!).score
        val aantal = ScoreFragmentArgs.fromBundle(arguments!!).aantalVragen
        binding.txtScore.setText("Uw score op de quiz is: " + score + " op: " + aantal)
        return binding.root
        }
}