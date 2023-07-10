package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.viewmodel.GuessViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this)[GuessViewModel::class.java]
        viewModel.message.observe(this){ binding.resultTextView.text = it }
        viewModel.times.observe(this){ binding.CounterTextView.text = it.toString() }
        binding.guessButton.setOnClickListener { viewModel.guess(binding.editTextNum.text.toString().toInt()) }
    }
}