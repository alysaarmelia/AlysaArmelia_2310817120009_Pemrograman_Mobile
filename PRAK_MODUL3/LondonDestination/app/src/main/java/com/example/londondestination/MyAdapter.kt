package com.example.londondestination

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.londondestination.databinding.ItemListBinding

class MyAdapter(
    private val destinations: List<MyData>,
    private val onDetailClick: (MyData) -> Unit
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinations[position]
        with(holder.binding) {
            textViewName.text = destination.nama
            textViewYear.text = destination.year.toString()
            textViewDesc.text = destination.descriptionsingkat
            imageView.setImageResource(destination.image)

            buttonLink.setOnClickListener {
                val context = it.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(destination.link))
                context.startActivity(intent)
            }

            buttonDetail.setOnClickListener {
                onDetailClick(destination)
            }
        }
    }

    override fun getItemCount(): Int = destinations.size
}