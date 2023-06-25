package com.example.kks_newcomer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.kks_newcomer.R
import com.example.kks_newcomer.databinding.PagingLoadStateBinding

class PagingLoadStateViewHolder(
    private val binding: PagingLoadStateBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PagingLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.paging_load_state, parent, false)
            val binding = PagingLoadStateBinding.bind(view)
            return PagingLoadStateViewHolder(binding, retry)
        }
    }
}