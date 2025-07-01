package com.example.myapi_test.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapi_test.R
import com.example.myapi_test.databinding.RegisterFragmentBinding
import com.example.myapi_test.presentation.viewmodel.RegisterViewModel
import com.example.myapi_test.presentation.viewmodel.RegisterViewModelFactory

class RegisterFragment : Fragment() {

    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = RegisterViewModelFactory(requireActivity().application)
        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        setupToolbar()
        setupListeners()
        observeViewModel()
    }

    private fun setupToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.registerToolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = getString(R.string.register_title)
        binding.registerToolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupListeners() {
        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextPasswordConfirm.text.toString().trim()

            registerViewModel.register(email, password, confirmPassword)
        }
    }

    private fun observeViewModel() {
        registerViewModel.registerState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RegisterViewModel.RegisterState.Success -> {
                    Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                }
                is RegisterViewModel.RegisterState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                }
                is RegisterViewModel.RegisterState.Loading -> {
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}