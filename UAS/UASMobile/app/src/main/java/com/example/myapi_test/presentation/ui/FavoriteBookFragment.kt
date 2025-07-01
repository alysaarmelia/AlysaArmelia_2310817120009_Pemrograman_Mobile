package com.example.myapi_test.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapi_test.R
import com.example.myapi_test.databinding.FragmentFavoriteBinding
import com.example.myapi_test.presentation.model.BookUi
import com.example.myapi_test.presentation.viewmodel.FavoriteBookViewModel
import com.example.myapi_test.presentation.viewmodel.FavoriteBookViewModelFactory

class FavoriteBookFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FavoriteBookViewModel
    private lateinit var favoriteBookAdapter: FavoriteBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = FavoriteBookViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[FavoriteBookViewModel::class.java]

        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.favoriteToolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.favoriteToolbar.setNavigationOnClickListener {
            activity.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        favoriteBookAdapter = FavoriteBookAdapter { book -> navigateToDetail(book) }
        binding.recyclerViewFavorites.adapter = favoriteBookAdapter
    }

    private fun observeViewModel() {
        viewModel.favoriteBooks.observe(viewLifecycleOwner) { favorites ->
            favoriteBookAdapter.submitList(favorites)
            binding.textViewEmptyState.visibility = if (favorites.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    private fun navigateToDetail(book: BookUi) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DetailFragment.newInstance(book))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
