//
//  HomeCombineViewModel.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 12/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Combine
import Factory
import KMPNativeCoroutinesCombine
import shared

final class HomeCombineViewModel: ObservableObject {

    @Published var isLoading = true
    @Published var userAvatarUrl = ""
    @Published var surveys: [SurveyUiModel] = []
    @Published var today = ""

    @Injected(\.homeViewModel) private var homeViewModel
    @Published private(set) var viewState = LogInViewState()
    private var cancellables = Set<AnyCancellable>()

    init() {
        createPublisher(for: homeViewModel.viewStateNative)
            .catch { _ -> Just<HomeViewState> in
                let homeViewState = HomeViewState()
                return Just(homeViewState)
            }
            .receive(on: DispatchQueue.main)
            .sink { [weak self] value in
                self?.updateViewState(value)
            }
            .store(in: &cancellables)
    }

    func fetchData() {
        homeViewModel.fetchData()
    }

    func loadMoreSurvey(selectedIndex: Int) {
        homeViewModel.fetchMoreSurveysIfNeeded(selectedIndex: Int32(selectedIndex))
    }

    func getSurveyWith(id: String) -> Survey? {
        homeViewModel.surveys.first(where: { $0.id == id })
    }

    private func updateViewState(_ state: HomeViewState) {
        isLoading = state.isLoading
        surveys = state.surveys
        userAvatarUrl = state.headerUiModel?.imageUrl ?? ""
        today = state.headerUiModel?.dateText ?? ""
    }
}
