package com.example.diceroller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: Dice_ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.rollDice()
        }

        viewModel.dice1.observe(this) { value ->
            binding.imageView.setImageResource(getDiceImage(value))
        }

        viewModel.dice2.observe(this) { value ->
            binding.imageView2.setImageResource(getDiceImage(value))
        }

        viewModel.isDouble.observe(this) { isDouble ->
            val message = if (isDouble) {
                "Selamat anda dapat dadu double!"
            } else {
                "Anda belum beruntung!"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDiceImage(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}