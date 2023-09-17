package com.example.kks_newcomer.ui.home

import android.util.Log
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kks_newcomer.data.Attraction

class AttractionPagingAdapter(private val onClick: (Attraction) -> Unit) :
    PagingDataAdapter<Attraction, AttractionPagingAdapter.AttractionPagingViewHolder>(DIFF_CALLBACK) {

    class AttractionPagingViewHolder(private val composeView: ComposeView, private val onClick: (Attraction) -> Unit) :
        RecyclerView.ViewHolder(composeView) {
        private var currentAttraction: Attraction? = null
        fun bind(attraction: Attraction) {
            currentAttraction = attraction
            composeView.setContent {
                ItemListAttraction(attraction, onClick)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionPagingViewHolder {
        return AttractionPagingViewHolder(ComposeView(parent.context), onClick)
    }

    override fun onBindViewHolder(holder: AttractionPagingViewHolder, position: Int) {
        getItem(position)?.run {
            holder.bind(this)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Attraction>() {
            override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
                return oldItem == newItem
            }
        }
    }
}