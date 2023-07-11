package com.example.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GuessViewModel():ViewModel()
{
    val message = MutableLiveData<String>()
    val times = MutableLiveData<Int>()
    val resetState = MutableLiveData<Boolean>(false)
    private var answer = Random.nextInt(100)+1
    private var counter = 0

    fun buttonClicked(num: Int?)
    {
        if (resetState.value==false)
        {
            num?.let { guess(num.toInt()) }
        }
        else
            reset()
    }

    fun guess(num:Int)
    {
        if (resetState.value==false)
        {
            message.value = when(num-answer)
            {
                0 -> {resetState.value = true
                    "答對了!"}
                in 1..100 -> "再小一點"
                else -> "大一點"
            }
            counter++

        }
        times.value = counter
    }

    fun reset()
    {
        counter = 0
        answer = Random.nextInt(100)+1
        message.value = "重新開始!"
        resetState.value = false
        times.value = counter
    }
}