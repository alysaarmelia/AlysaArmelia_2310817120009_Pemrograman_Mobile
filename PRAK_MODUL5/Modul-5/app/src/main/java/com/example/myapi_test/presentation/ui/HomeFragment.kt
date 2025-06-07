package com.example.myapi_test.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapi_test.R
import com.example.myapi_test.databinding.HomeFragmentBinding
import com.example.myapi_test.presentation.model.BookUi
import com.example.myapi_test.presentation.viewmodel.BookViewModel

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookViewModel: BookViewModel
    private lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Share the ViewModel with the hosting Activity
        bookViewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)

        setupRecyclerView()
        observeViewModel()

        // Fetch data only if the list is empty
        if (bookViewModel.booksLiveData.value.isNullOrEmpty()) {
            bookViewModel.fetchBooks()
        }
    }

    private fun setupRecyclerView() {
        myAdapter = MyAdapter(
            mutableListOf(),
            onDetailButtonClicked = { book -> navigateToDetail(book) },
            onInfoButtonClicked = { book -> openWikiLink(book) },
            onItemRootClicked = { book -> navigateToDetail(book) }
        )

        binding.rvCharacter.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }
    }

    private fun observeViewModel() {
        // Observe the list of books and update the adapter
        bookViewModel.booksLiveData.observe(viewLifecycleOwner) { books ->
            books?.let { myAdapter.updateBooks(it) }
        }

        // The MainActivity handles observing isLoading and errorMessage for global UI feedback
    }

    private fun navigateToDetail(book: BookUi) {
        Log.d("HomeFragment", "Navigating to detail for: ${book.title}")
        val detailFragment = DetailFragment.newInstance(book)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun openWikiLink(book: BookUi) {
        Log.d("HomeFragment", "Info button clicked for: ${book.title}")
        if (book.wiki.isNotBlank()) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.wiki))
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("HomeFragment", "Could not open URL: ${book.wiki}", e)
                Toast.makeText(requireContext(), "Could not open link.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "No info link available.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}