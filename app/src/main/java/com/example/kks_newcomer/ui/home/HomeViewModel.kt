package com.example.kks_newcomer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.kks_newcomer.data.Attraction
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

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

//    fun fetchAllAttractions2(lang: String = "zh-tw", page: Int = 1): Flow<PagingData<Attraction>> {
//        return fetchAllAttractionsUseCase.tryPaging()
//    }

    fun fetchAllAttractions2(lang: String = "zh-tw", page: Int = 1) = viewModelScope.launch {
        _viewState.emit(HomeViewState.Loading)
        try {
            fetchAllAttractionsUseCase.tryPaging(page = page).distinctUntilChanged().collectLatest {
                _viewState.emit(HomeViewState.AllAttractionsDataReady2(data = it))
            }
        } catch (exception: Exception) {
            Timber.e("error: $exception")
            _viewState.emit(HomeViewState.Error)
        }

    }

    private var _category: MutableStateFlow<List<Category>> = MutableStateFlow(listOf())
    val category: Flow<List<Category>> = _category

}