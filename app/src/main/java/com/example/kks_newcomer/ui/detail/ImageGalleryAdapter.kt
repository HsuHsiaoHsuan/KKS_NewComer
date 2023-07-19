package com.example.kks_newcomer.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.kks_newcomer.databinding.ItemImageCarouselBinding

class ImageGalleryAdapter(private val dataSet: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageGalleryAdapter.ViewHolder {
        val binding =
            ItemImageCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataSet[position]
        (holder as ImageGalleryAdapter.ViewHolder).bind(data)
    }

    override fun getItemCount(): Int = dataSet.size

    class ViewHolder(private val binding: ItemImageCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String) {
            binding.image.load(data) {
                scale(Scale.FILL)
            }
        }
    }
}