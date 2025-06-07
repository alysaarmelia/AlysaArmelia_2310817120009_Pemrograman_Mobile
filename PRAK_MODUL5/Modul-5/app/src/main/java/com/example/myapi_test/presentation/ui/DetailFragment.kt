package com.example.myapi_test.presentation.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapi_test.R
import com.example.myapi_test.databinding.DetailFragmentBinding
import com.example.myapi_test.presentation.model.BookUi

class DetailFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private var currentBook: BookUi? = null

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

        val toolbar: androidx.appcompat.widget.Toolbar = view.findViewById(R.id.detailToolbar)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            // Handle back press
            activity.onBackPressed()
        }
        currentBook?.let { book ->
            binding.detailTitle.text = book.title
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