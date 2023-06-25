package com.example.kks_newcomer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kks_newcomer.R
import com.example.kks_newcomer.databinding.FragmentHomeBinding
import com.example.kks_newcomer.databinding.PagingLoadStateBinding
import com.example.kks_newcomer.ui.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val attractionPagingAdapter = AttractionPagingAdapter { attraction ->
        Timber.d(attraction.toString())
        findNavController().navigate(R.id.detailFragment)
    }.apply {
        withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter { this.retry() },
            footer = PagingLoadStateAdapter { this.retry() }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.list.adapter = attractionPagingAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.viewState.collectLatest {
                    when (it) {
                        is HomeViewState.Nothing -> Unit
                        is HomeViewState.Loading -> Unit
                        is HomeViewState.AllAttractionsDataReady -> {
                            attractionPagingAdapter.submitData(it.data)
                        }
                        is HomeViewState.Error -> {
                            Timber.e("Error")
                        }
                    }
                }
            }
        }
        viewModel.fetchAllAttractionsPaged()
    }
}