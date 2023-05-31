package com.example.kks_newcomer.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kks_newcomer.data.Attraction
import com.example.kks_newcomer.databinding.ItemListAttractionBinding

class AttractionAdapter(private val onClick: (Attraction) -> Unit) :
    ListAdapter<Attraction, AttractionAdapter.AttractionViewHolder>(AttractionDiffCallback) {

    class AttractionViewHolder(private val binding: ItemListAttractionBinding, private val onClick: (Attraction) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var currentAttraction: Attraction? = null
        init {
            binding.root.setOnClickListener {
                currentAttraction?.let {
                    onClick(it)
                }
            }
        }
        fun bind(attraction: Attraction) {
            currentAttraction = attraction
            binding.textTitle.text = attraction.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val binding = ItemListAttractionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object AttractionDiffCallback : DiffUtil.ItemCallback<Attraction>() {
        override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem == newItem
        }

    }
}