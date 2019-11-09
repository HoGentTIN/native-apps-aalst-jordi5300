package com.example.quizzit

import androidx.navigation.findNavController
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quizzit.databinding.FragmentHomeBinding
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var resultImageView: ImageView
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
           binding.playButton.setOnClickListener { view ->
               view.findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
           }
           binding.quizzitImage.setImageResource(R.drawable.quizzit)
           (activity as AppCompatActivity).supportActionBar?.title = "Quizzit"
           setHasOptionsMenu(true)
           return binding.root
       }
}
