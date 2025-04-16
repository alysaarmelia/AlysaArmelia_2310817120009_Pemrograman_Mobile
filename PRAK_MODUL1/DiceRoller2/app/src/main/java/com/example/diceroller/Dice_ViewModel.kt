package com.example.diceroller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Dice_ViewModel : ViewModel() {

    private val _dice1 = MutableLiveData(1)
    val dice1: LiveData<Int> = _dice1

    private val _dice2 = MutableLiveData(1)
    val dice2: LiveData<Int> = _dice2

    private val _isDouble = MutableLiveData(false)
    val isDouble: LiveData<Boolean> = _isDouble

    fun rollDice() {
        val d1 = (1..6).random()
        val d2 = (1..6).random()
        _dice1.value = d1
        _dice2.value = d2
        _isDouble.value = (d1 == d2)
    }
}