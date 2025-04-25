package com.example.kalkulatortip

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.kalkulatortip.databinding.ActivtySplashBinding


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivtySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivtySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            goToMainActivity()
        },3000L)
    }

    private fun goToMainActivity(){
        Intent(this,MainActivity::class.java).also{
            startActivity(it)
            finish()
        }
    }
}