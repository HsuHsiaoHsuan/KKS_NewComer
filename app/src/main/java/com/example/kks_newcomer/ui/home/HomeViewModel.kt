package com.example.kks_newcomer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kks_newcomer.data.Category
import com.example.kks_newcomer.domain.FetchAllAttractionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchAllAttractionsUseCase: FetchAllAttractionsUseCase
) : ViewModel() {

    private var _viewState: MutableStateFlow<HomeViewState> =
        MutableStateFlow(HomeViewState.Nothing)
    val viewState: Flow<HomeViewState> = _viewState

    fun fetchAllAttractions(lang: String = "zh-tw", page: Int = 1) = viewModelScope.launch {
        _viewState.emit(HomeViewState.Loading)
        fetchAllAttractionsUseCase(lang = lang, page = page).onEach {
            _viewState.emit(HomeViewState.AllAttractionsDataReady(data = it))
        }.catch {
            Timber.e("error: $it")
            _viewState.emit(HomeViewState.Error)
        }.launchIn(viewModelScope)
    }

    private var _category: MutableStateFlow<List<Category>> = MutableStateFlow(listOf())
    val category: Flow<List<Category>> = _category

}