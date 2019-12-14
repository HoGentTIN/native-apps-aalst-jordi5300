package com.example.quizzit

import androidx.navigation.findNavController
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quizzit.databinding.FragmentHomeBinding
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : Fragment() {
       override fun onCreateView(
           inflater: LayoutInflater, container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {
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
