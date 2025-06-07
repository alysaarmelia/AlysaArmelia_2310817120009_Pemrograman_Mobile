package com.example.myapi_test.presentation.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapi_test.R
import com.example.myapi_test.databinding.ItemListBinding
import com.example.myapi_test.presentation.model.BookUi

class MyAdapter(
    private var books: MutableList<BookUi>,
    private val onDetailButtonClicked: (book: BookUi) -> Unit,
    private val onInfoButtonClicked: (book: BookUi) -> Unit,
    private val onItemRootClicked: (book: BookUi) -> Unit
) : RecyclerView.Adapter<MyAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // Listener for the "Detail" button
            binding.buttonInfo.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDetailButtonClicked(books[position])
                }
            }

            // Listener for the "Info" button
            binding.buttonDetail.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onInfoButtonClicked(books[position])
                }
            }

            // Listener for the entire item click
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemRootClicked(books[position])
                }
            }
        }

        fun bind(book: BookUi) {
            binding.apply {
                textViewName.text = book.title
                textViewYear.text = book.release_date
                textViewAuthor.text = book.author

                Glide.with(itemView.context)
                    .load(book.cover)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    fun updateBooks(newBooks: List<BookUi>) {
        this.books.clear()
        this.books.addAll(newBooks)
        notifyDataSetChanged() // For production apps, consider using DiffUtil for better performance
    }
}