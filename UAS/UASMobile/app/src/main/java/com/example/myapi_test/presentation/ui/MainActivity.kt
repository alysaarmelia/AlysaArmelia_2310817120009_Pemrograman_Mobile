package com.example.myapi_test.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.myapi_test.R
import com.example.myapi_test.data.util.SessionManager
import com.example.myapi_test.databinding.ActivityMainBinding
import com.example.myapi_test.presentation.viewmodel.BookViewModel
import com.example.myapi_test.presentation.viewmodel.BookViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = BookViewModelFactory(application)
        bookViewModel = ViewModelProvider(this, viewModelFactory)[BookViewModel::class.java]

        splashScreen.setKeepOnScreenCondition {
            bookViewModel.isLoadingLiveData.value == true
        }

        if (savedInstanceState == null) {
            if (SessionManager.isLoggedIn(this)) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, HomeFragment())
                    .commitNow()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, LoginFragment())
                    .commitNow()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        bookViewModel.isLoadingLiveData.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        bookViewModel.errorLiveData.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun navigateToLogin() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, LoginFragment())
            .commit()
    }
}
