package com.example.myapi_test.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapi_test.databinding.ItemListBinding
import com.example.myapi_test.presentation.model.BookUi

class MyAdapter(
    private val onDetailButtonClicked: (book: BookUi) -> Unit,
    private val onInfoButtonClicked: (book: BookUi) -> Unit,
    private val onItemRootClicked: (book: BookUi) -> Unit
) : ListAdapter<BookUi, MyAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonDetail.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDetailButtonClicked(getItem(position))
                }
            }

            binding.buttonInfo.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onInfoButtonClicked(getItem(position))
                }
            }

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemRootClicked(getItem(position))
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
}

class BookDiffCallback : DiffUtil.ItemCallback<BookUi>() {
    override fun areItemsTheSame(oldItem: BookUi, newItem: BookUi): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: BookUi, newItem: BookUi): Boolean {
        return oldItem == newItem
    }
}
