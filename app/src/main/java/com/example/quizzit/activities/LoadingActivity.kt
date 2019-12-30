package com.example.quizzit.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.quizzit.R
import com.example.quizzit.databinding.ActivityLoadingBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LoadingActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding
    private val loadingViewModel: LoadingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_loading)

        loadingViewModel.isLoading.observe(this, Observer { loadingResult ->
            setResult(loadingResult)
            finish()
        })
    }
}