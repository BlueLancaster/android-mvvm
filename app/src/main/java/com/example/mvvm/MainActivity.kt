package com.example.mvvm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.viewmodel.GuessViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this)[GuessViewModel::class.java]
        viewModel.message.observe(this){ binding.resultTextView.text = it }
        viewModel.times.observe(this){ binding.CounterTextView.text = it.toString() }
        viewModel.resetState.observe(this){
            if (it==true)
            {
                binding.guessButton.text = "Reset"
                binding.editTextNum.text = null
                binding.editTextNum.clearFocus()
                binding.guessButton.isEnabled = true
                binding.editTextNum.isEnabled = false
            }
            else
            {
                binding.guessButton.isEnabled = false
                binding.editTextNum.isEnabled = true
                binding.guessButton.text = "Guess"
            }
        }
        binding.editTextNum.addTextChangedListener {
            binding.guessButton.isEnabled =
                binding.editTextNum.text.toString().toIntOrNull() != null
        }

        binding.guessButton.setOnClickListener{
            viewModel.buttonClicked(binding.editTextNum.text.toString().toIntOrNull())
        }
    }
}