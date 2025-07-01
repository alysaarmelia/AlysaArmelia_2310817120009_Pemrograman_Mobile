package com.example.myapi_test.presentation.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.myapi_test.R
import com.example.myapi_test.databinding.DetailFragmentBinding
import com.example.myapi_test.presentation.model.BookUi
import com.example.myapi_test.presentation.viewmodel.DetailViewModel
import com.example.myapi_test.presentation.viewmodel.DetailViewModelFactory

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private var currentBook: BookUi? = null
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentBook = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(ARG_BOOK, BookUi::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getParcelable(ARG_BOOK)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = DetailViewModelFactory(requireActivity().application)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        setupToolbar()
        populateUi()
        observeFavoriteStatus()
        setupFavoriteButton()
    }

    private fun setupToolbar() {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.detailToolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.detailToolbar.setNavigationOnClickListener {
            activity.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun populateUi() {
        currentBook?.let { book ->
            binding.detailToolbar.title = book.title
            binding.detailTitle.text = book.title
            binding.detailAuthor.text = book.author
            binding.detailReleaseDate.text = book.release_date
            binding.detailDescription.text = book.summary

            if (book.cover.isNotBlank()) {
                Glide.with(this)
                    .load(book.cover)
                    .into(binding.detailImage)
            } else {
                binding.detailImage.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }

    private fun observeFavoriteStatus() {
        currentBook?.let { book ->
            detailViewModel.isFavorite(book.title).observe(viewLifecycleOwner) { isFavorite ->
                val iconRes = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
                binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(requireContext(), iconRes))
            }
        }
    }

    private fun setupFavoriteButton() {
        binding.fabFavorite.setOnClickListener {
            currentBook?.let { book ->
                detailViewModel.toggleFavoriteStatus(book.title)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_BOOK = "book_ui_parcel"

        @JvmStatic
        fun newInstance(book: BookUi): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_BOOK, book)
                }
            }
        }
    }
}