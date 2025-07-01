package com.example.myapi_test.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.example.myapi_test.R
import com.example.myapi_test.data.util.SessionManager
import com.example.myapi_test.databinding.HomeFragmentBinding
import com.example.myapi_test.presentation.model.BookUi
import com.example.myapi_test.presentation.viewmodel.BookViewModel
import com.example.myapi_test.presentation.viewmodel.BookViewModelFactory

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

        (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
        setupMenu()

        val factory = BookViewModelFactory(requireActivity().application)
        bookViewModel = ViewModelProvider(this, factory)[BookViewModel::class.java]

        setupRecyclerView()
        observeViewModel()

        if (bookViewModel.booksLiveData.value.isNullOrEmpty()) {
            bookViewModel.fetchBooks()
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_favorites -> {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, FavoriteBookFragment())
                            .addToBackStack(null)
                            .commit()
                        true
                    }
                    // This block handles the logout action
                    R.id.action_logout -> {
                        // Clear the user's session data
                        SessionManager.clearSession(requireContext())
                        // Navigate back to the login screen using the method in MainActivity
                        (activity as? MainActivity)?.navigateToLogin()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupRecyclerView() {
        myAdapter = MyAdapter(
            onDetailButtonClicked = { book -> navigateToDetail(book) },
            onInfoButtonClicked = { book -> openWikiLink(book) },
            onItemRootClicked = { book -> navigateToDetail(book) }
        )
        binding.rvCharacter.adapter = myAdapter
    }

    private fun observeViewModel() {
        bookViewModel.booksLiveData.observe(viewLifecycleOwner) { books ->
            books?.let { myAdapter.submitList(it) }
        }
    }

    private fun navigateToDetail(book: BookUi) {
        val detailFragment = DetailFragment.newInstance(book)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun openWikiLink(book: BookUi) {
        if (book.wiki.isNotBlank()) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.wiki))
                startActivity(intent)
            } catch (e: Exception) {
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