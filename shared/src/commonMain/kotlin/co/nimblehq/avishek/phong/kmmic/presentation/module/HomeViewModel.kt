package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetSurveysUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

private const val DEFAULT_PAGE_SIZE = 10

data class HomeViewState(
    val isLoading: Boolean
) {
    constructor() : this (true)
}

class HomeViewModel(
    private val getSurveysUseCase: GetSurveysUseCase
) : BaseViewModel() {

    private val _viewState: MutableStateFlow<HomeViewState> =
        MutableStateFlow(HomeViewState())

    val viewState: StateFlow<HomeViewState> = _viewState

    private var currentPage = 1

    fun fetchData() {
        getSurveysUseCase(currentPage, DEFAULT_PAGE_SIZE, isForceLatestData = false)
            .onStart { setStateLoading() }
            .catch { emit(emptyList()) }
            .onEach {
                currentPage++
                handleFetchSuccess()
            }
            .launchIn(viewModelScope)
    }

    private fun fetchSurvey(
        page: Int,
        pageSize: Int,
        isForceLatestData: Boolean
    ): Flow<List<Survey>> {
        return flow {
            getSurveysUseCase(page, pageSize, isForceLatestData = false)
                .catch { emit(listOf()) }
                .collect {
                    currentPage = page+1
                    emit(it)
                }
        }
    }

    private fun setStateLoading() {
        _viewState.update {
            HomeViewState(isLoading = true)
        }
    }

    private fun handleFetchSuccess() {
        _viewState.update {
            HomeViewState(isLoading = false)
        }
    }
}
