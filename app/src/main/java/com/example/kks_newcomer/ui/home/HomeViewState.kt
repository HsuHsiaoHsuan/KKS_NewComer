package com.example.kks_newcomer.ui.home

import androidx.paging.PagingData
import com.example.kks_newcomer.data.Attraction

sealed class HomeViewState {
    object Nothing : HomeViewState()
    object Loading : HomeViewState()
    data class AllAttractionsDataReady(val data: List<Attraction>) : HomeViewState()
    data class AllAttractionsDataReady2(val data: PagingData<Attraction>) : HomeViewState()
    object Error : HomeViewState()
}