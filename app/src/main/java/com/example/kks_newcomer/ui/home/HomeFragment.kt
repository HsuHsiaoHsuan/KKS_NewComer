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
import com.example.kks_newcomer.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val attractionAdapter = AttractionAdapter { attraction ->
        Timber.d(attraction.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.list.adapter = attractionAdapter

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
                            attractionAdapter.submitList(it.data)
                        }
                        is HomeViewState.Error -> {
                            Timber.e("Error")
                        }

                    }
                }
            }
        }
//        viewModel.fetchCategory()
        viewModel.fetchAllAttractions()
    }
}