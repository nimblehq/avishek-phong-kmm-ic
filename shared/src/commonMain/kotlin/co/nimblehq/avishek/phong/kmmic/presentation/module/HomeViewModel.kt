package co.nimblehq.avishek.phong.kmmic.presentation.module

import co.nimblehq.avishek.phong.kmmic.domain.model.Survey
import co.nimblehq.avishek.phong.kmmic.domain.model.User
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTime
import co.nimblehq.avishek.phong.kmmic.domain.platform.datetime.DateTimeFormatter
import co.nimblehq.avishek.phong.kmmic.domain.usecase.*
import co.nimblehq.avishek.phong.kmmic.presentation.uimodel.*
import kotlinx.coroutines.flow.*

private const val LOAD_MORE_THRESHOLD = 2
private const val DEFAULT_PAGE_SIZE = 5

data class HomeViewState(
    val isLoading: Boolean,
    val isRefreshing: Boolean,
    val headerUiModel: SurveyHeaderUiModel? = null,
    var surveys: List<SurveyUiModel> = listOf(),
) {
    constructor() : this(true, false)
}

class HomeViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getAppVersionUseCase: GetAppVersionUseCase,
    private val dateTime: DateTime,
    private val dateTimeFormatter: DateTimeFormatter,
) : BaseViewModel() {

    private val _viewState: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState> = _viewState

    private val _appVersion: MutableStateFlow<String> = MutableStateFlow("")
    val appVersion: StateFlow<String> = _appVersion

    private var previousSelectedIndex = 0
    private var currentPage = 1

    fun fetchData() {
        getProfile()
            .onStart { setStateLoading() }
            .combine(fetchSurvey(page = currentPage)) { user, surveys ->
                Pair(user, surveys)
            }
            .onEach { (user, surveys) ->
                handleFetchUserProfileSuccess(user)
                handleFetchSurveysSuccess(surveys)
            }
            .launchIn(viewModelScope)

        _appVersion.update { getAppVersionUseCase() }
    }

    fun fetchMoreSurveysIfNeeded(selectedIndex: Int) {
        if (selectedIndex != _viewState.value.surveys.count() - 1 - LOAD_MORE_THRESHOLD
            || previousSelectedIndex >= selectedIndex
        ) {
            previousSelectedIndex = selectedIndex
            return
        }
        previousSelectedIndex = selectedIndex
        fetchSurvey(currentPage)
            .onEach {
                handleFetchMoreSurveySuccess(it)
            }
            .launchIn(viewModelScope)
    }

    fun refresh() {
        currentPage = 1
        fetchSurvey(
            page = currentPage,
            isForceLatestData = true
        )
            .onStart { setStateRefreshing() }
            .onEach {
                handleFetchSurveysSuccess(it)
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
        pageSize: Int = DEFAULT_PAGE_SIZE,
        isForceLatestData: Boolean = false,
    ): Flow<List<Survey>> {
        return getSurveysUseCase(
            pageNumber = page,
            pageSize = pageSize,
            isForceLatestData = isForceLatestData
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
            HomeViewState(
                isLoading = false,
                isRefreshing = false,
                headerUiModel = headerUiModel)
        }
    }

    private fun handleFetchMoreSurveySuccess(surveys: List<Survey>) {
        val surveyUiModels = surveys.map { SurveyUiModel(it) }
        _viewState.update {
            HomeViewState(
                isLoading = false,
                isRefreshing = false,
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
                isRefreshing = false,
                headerUiModel = it.headerUiModel,
                surveys = surveyUiModels
            )
        }
    }

    private fun setStateLoading() {
        _viewState.update {
            HomeViewState(
                isLoading = true,
                isRefreshing = false,
                headerUiModel = it.headerUiModel,
                surveys = it.surveys
            )
        }
    }

    private fun setStateRefreshing() {
        _viewState.update {
            HomeViewState(
                isLoading = false,
                isRefreshing = true,
                headerUiModel = it.headerUiModel,
                surveys = it.surveys
            )
        }
    }
}
