package com.example.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GuessViewModel():ViewModel()
{
    val message = MutableLiveData<String>()
    val times = MutableLiveData<Int>()
    private var answer = Random.nextInt(100)+1
    private var counter = 0

    fun guess(num:Int)
    {
        message.value = when(num-answer)
        {
            0 -> "答對了!"
            in 1..100 -> "再小一點"
            else -> "大一點"
        }
        counter++
        times.value = counter
    }
}