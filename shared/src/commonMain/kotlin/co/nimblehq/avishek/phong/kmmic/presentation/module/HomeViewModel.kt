package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.model.User
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateFormat
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTime
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetSurveysUseCase
import co.nimblehq.avishek.phong.kmmic.domain.usecase.GetUserProfileUseCase
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyHeaderUiModel
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.SurveyUiModel
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.toSurveyHeaderUiModel
import kotlinx.coroutines.flow.*

private const val LOAD_MORE_THRESHOLD = 2
private const val DEFAULT_PAGE_SIZE = 5

data class HomeViewState(
    val isLoading: Boolean,
    val headerUiModel: SurveyHeaderUiModel? = null,
    var surveys: List<SurveyUiModel> = listOf()
) {
    constructor() : this (true)
}

class HomeViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getSurveysUseCase: GetSurveysUseCase,
    private val dateTime: DateTime,
    private val dateTimeFormatter: DateTimeFormatter,
) : BaseViewModel() {

    private val _viewState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())

    val viewState: StateFlow<HomeViewState> = _viewState

    private var previousSelectedIndex = 0
    private var currentPage = 1

    fun fetchData() {
        getProfile()
            .onStart { setStateLoading() }
            .combine(fetchSurvey(
                page = currentPage,
                pageSize = DEFAULT_PAGE_SIZE,
                isForceLatestData = false
            )) { user, surveys ->
                Pair(user, surveys)
            }
            .onEach {
                handleFetchUserProfileSuccess(it.first)
                handleFetchSurveysSuccess(it.second)
            }
            .launchIn(viewModelScope)
    }

    fun fetchMoreSurveysIfNeeded(selectedIndex: Int) {
        if (selectedIndex != _viewState.value.surveys.count() - 1 - LOAD_MORE_THRESHOLD
            || previousSelectedIndex >= selectedIndex) {
            previousSelectedIndex = selectedIndex
            return
        }
        previousSelectedIndex = selectedIndex
        fetchSurvey(currentPage, DEFAULT_PAGE_SIZE, false)
            .onEach {
                handleFetchMoreSurveySuccess(it)
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
        return getSurveysUseCase(
            pageNumber = page,
            pageSize =  pageSize,
            isForceLatestData = false
        )
            .catch { emit(emptyList()) }
            .onEach {
                currentPage = page + 1
            }
    }

    private fun handleFetchUserProfileSuccess(user: User?) {
        val today = dateTime.today()
        val headerUiModel = user?.toSurveyHeaderUiModel(today, dateTimeFormatter)

        _viewState.update {
            HomeViewState(isLoading = false, headerUiModel)
        }
    }

    private fun handleFetchMoreSurveySuccess(surveys: List<Survey>) {
        val surveyUiModels = surveys.map { SurveyUiModel(it) }
        _viewState.update {
            HomeViewState(
                isLoading = false,
                headerUiModel = it.headerUiModel,
                surveys = it.surveys + surveyUiModels
            )
        }
    }

    private fun handleFetchSurveysSuccess(surveys: List<Survey>) {
        val surveyUiModels = surveys.map { SurveyUiModel(it) }
        _viewState.update {
            HomeViewState(
                isLoading = false,
                headerUiModel = it.headerUiModel,
                surveys = surveyUiModels
            )
        }
    }

    private fun setStateLoading() {
        _viewState.update {
            HomeViewState(
                isLoading = true,
                headerUiModel = it.headerUiModel,
                surveys = it.surveys
            )
        }
    }
}
