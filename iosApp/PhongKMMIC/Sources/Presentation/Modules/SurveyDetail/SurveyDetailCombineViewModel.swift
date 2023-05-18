//
//  SurveyDetailCombineViewModel.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 17/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Combine
import Factory
import KMPNativeCoroutinesCombine
import shared

final class SurveyDetailCombineViewModel: ObservableObject {

    @Published var isLoading = false
    @Published var errorMessage: String?
    @Published var backgroundImageUrl: String = ""
    @Published var title: String?
    @Published var description: String?

    @Injected(\.surveyDetailViewModel) private var surveyDetailViewModel
    @Published private(set) var viewState = SurveyDetailViewState()
    private var cancellables = Set<AnyCancellable>()

    init(survey: SurveyUiModel) {
        backgroundImageUrl = survey.largeImageUrl
        title = survey.title
        description = survey.description_

        createPublisher(for: surveyDetailViewModel.viewSateNative)
            .catch { error -> Just<SurveyDetailViewState> in
                let viewState = SurveyDetailViewState(errorMessage: error.localizedDescription)
                return Just(viewState)
            }
            .receive(on: DispatchQueue.main)
            .sink { [weak self] value in
                self?.updateState(value)
            }
            .store(in: &cancellables)
    }

    func fetchSurvey() {
        surveyDetailViewModel.fetchSurveyDetail(id: "")
    }

    private func updateState(_ state: SurveyDetailViewState) {
        isLoading = state.isLoading
        errorMessage = state.errorMessage
    }
}
