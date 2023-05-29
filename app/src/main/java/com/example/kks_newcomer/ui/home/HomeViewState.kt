package com.example.kks_newcomer.ui.home

import com.example.kks_newcomer.data.Attractions

sealed class HomeViewState {
    object Nothing : HomeViewState()
    object Loading : HomeViewState()
    data class AllAttractionsDataReady(val data: List<Attractions>) : HomeViewState()
    object Error : HomeViewState()
}