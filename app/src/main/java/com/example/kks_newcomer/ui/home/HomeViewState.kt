package com.example.kks_newcomer.ui.home

import com.example.kks_newcomer.data.Attraction

sealed class HomeViewState {
    object Nothing : HomeViewState()
    object Loading : HomeViewState()
    data class AllAttractionsDataReady(val data: List<Attraction>) : HomeViewState()
    object Error : HomeViewState()
}