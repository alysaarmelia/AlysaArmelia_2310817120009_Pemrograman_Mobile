package com.example.kalkulatortip

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kalkulatortip.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TipViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[TipViewModel::class.java]

        viewModel.lastTipResult?.let {
            binding.textLala.text = it
        }

        binding.button.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val costInput = binding.costInput.text.toString()
        val cost = costInput.toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            Toast.makeText(this, "Input tidak valid, silakan masukkan angka yang benar", Toast.LENGTH_SHORT).show()
            val errorResult = getString(R.string.tip_result, 0.0)
            binding.textLala.text = errorResult
            viewModel.lastTipResult = errorResult
            return
        }

        val tipPercentage = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radio_pirates -> 0.20
            R.id.radio_ninjas -> 0.18
            R.id.radio_Capucino -> 0.15
            else -> 0.15
        }

        var tip = cost * tipPercentage
        if (binding.saveSwitch.isChecked) {
            tip = ceil(tip)
        }

        val result = getString(R.string.tip_result, tip)
        binding.textLala.text = result
        viewModel.lastTipResult = result
    }
}