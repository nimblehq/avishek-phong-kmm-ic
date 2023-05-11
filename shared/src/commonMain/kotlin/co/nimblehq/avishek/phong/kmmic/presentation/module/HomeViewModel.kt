package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.model.User
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetSurveysUseCase
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetUserProfileUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.zip

private const val DEFAULT_PAGE_SIZE = 10

data class HomeViewState(
    val isLoading: Boolean
) {
    constructor() : this (true)
}

class HomeViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getSurveysUseCase: GetSurveysUseCase
) : BaseViewModel() {

    private val _viewState: MutableStateFlow<HomeViewState> =
        MutableStateFlow(HomeViewState())

    val viewState: StateFlow<HomeViewState> = _viewState

    private var currentPage = 1



    fun fetchData() {
        getProfile()
            .onStart { setStateLoading() }
            .combine(fetchSurvey(currentPage, DEFAULT_PAGE_SIZE, false)) { _, _ ->
                // TODO: Return values in a tuple
            }
            .catch {
                // TODO: Handle error in integration story
            }
            .catch {
                // TODO: Handle error in integration story
            }
            .onEach {
                handleFetchSuccess()
            }
            .launchIn(viewModelScope)
    }

    private fun getProfile(): Flow<User?> {
        return flow {
            getUserProfileUseCase()
                .catch { emit(null) }
                .collect { emit(it) }
        }
    }

    private fun fetchSurvey(
        page: Int,
        pageSize: Int,
        isForceLatestData: Boolean
    ): Flow<List<Survey>> {
        return getSurveysUseCase(page, pageSize, isForceLatestData = false)
            .catch { emit(emptyList()) }
            .onEach {
                currentPage = page + 1
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
