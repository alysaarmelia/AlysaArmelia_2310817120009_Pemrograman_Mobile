package com.example.myapi_test.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen // <-- Import this
import androidx.lifecycle.ViewModelProvider
import com.example.myapi_test.R
import com.example.myapi_test.databinding.ActivityMainBinding
import com.example.myapi_test.presentation.viewmodel.BookViewModel
import com.example.myapi_test.presentation.viewmodel.BookViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Handle the splash screen transition. Must be called before super.onCreate().
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Standard view binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel using the factory
        val viewModelFactory = BookViewModelFactory(application)
        bookViewModel = ViewModelProvider(this, viewModelFactory)[BookViewModel::class.java]

        // 2. Keep the splash screen visible until the initial data is ready.
        // This links the splash screen's duration to your app's actual loading state.
        splashScreen.setKeepOnScreenCondition {
            bookViewModel.isLoadingLiveData.value == true
        }

        // Add the main fragment only if the activity is newly created
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .commitNow()
        }

        observeViewModel()
    }

    /**
     * Sets up observers for global UI states like loading indicators and error messages.
     */
    private fun observeViewModel() {
        // Observe loading state to show/hide the ProgressBar
        bookViewModel.isLoadingLiveData.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe errors to show a Toast message
        bookViewModel.errorLiveData.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}