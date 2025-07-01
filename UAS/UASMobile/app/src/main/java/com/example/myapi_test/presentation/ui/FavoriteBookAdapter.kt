package com.example.myapi_test.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapi_test.databinding.ItemListBinding
import com.example.myapi_test.presentation.model.BookUi

class FavoriteBookAdapter(
    private val onItemClicked: (BookUi) -> Unit
) : ListAdapter<BookUi, FavoriteBookAdapter.FavoriteViewHolder>(FavoriteBookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FavoriteViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClicked(getItem(position))
                }
            }
        }

        fun bind(book: BookUi) {
            binding.textViewName.text = book.title
            binding.textViewAuthor.text = book.author
            binding.textViewYear.text = book.release_date

            Glide.with(itemView.context)
                .load(book.cover)
                .into(binding.imageView)

            binding.buttonRow.visibility = View.GONE
        }
    }
}

class FavoriteBookDiffCallback : DiffUtil.ItemCallback<BookUi>() {
    override fun areItemsTheSame(oldItem: BookUi, newItem: BookUi): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: BookUi, newItem: BookUi): Boolean {
        return oldItem == newItem
    }
}
