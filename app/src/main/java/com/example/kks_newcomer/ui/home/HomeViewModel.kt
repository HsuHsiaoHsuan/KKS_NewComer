package com.example.kks_newcomer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kks_newcomer.data.ApiResult
import com.example.kks_newcomer.data.Category
import com.example.kks_newcomer.data.TourRepository
import com.example.kks_newcomer.domain.FetchAllAttractionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tourRepository: TourRepository,
    private val fetchAllAttractionsUseCase: FetchAllAttractionsUseCase
) : ViewModel() {

    private var _viewState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState.Nothing)
    val viewState: Flow<HomeViewState> = _viewState

    fun fetchAllAttractions(lang: String = "zh-tw", page: Int = 1) = viewModelScope.launch {
        fetchAllAttractionsUseCase.invoke(lang = lang, page = page).collectLatest {
            when (it) {
                is ApiResult.Loading -> {
                    _viewState.emit(HomeViewState.Loading)
                }
                is ApiResult.Success -> {
                    _viewState.emit(HomeViewState.AllAttractionsDataReady(it.data.allAttractionsData))
                }
                is ApiResult.Failure,
                is ApiResult.NetworkError -> {
                    _viewState.emit(HomeViewState.Error)
                }
            }
        }
    }

    private var _category: MutableStateFlow<List<Category>> = MutableStateFlow(listOf())
    val category: Flow<List<Category>> = _category

    fun fetchCategory(lang: String = "zh-tw", type: String = "Attractions") = viewModelScope.launch {
        tourRepository.fetchCategory(lang, type).collectLatest {
//            _category.emit(it.categoryData.category?: listOf())
        }
    }


}