package com.example.quizzit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quizzit.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.btnPlay.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
        }
        binding.imgLogo.setImageResource(R.drawable.quizzit)
        (activity as AppCompatActivity).supportActionBar?.title = "Quizzit"
        setHasOptionsMenu(true)
        return binding.root
    }
}
