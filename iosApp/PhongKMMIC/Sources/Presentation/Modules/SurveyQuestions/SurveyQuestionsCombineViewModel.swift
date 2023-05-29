//
//  SurveyQuestionsCombineViewModel.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 19/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Combine
import Factory
import KMPNativeCoroutinesCombine
import shared

final class SurveyQuestionsCombineViewModel: ObservableObject {

    @Published var questions: [QuestionUiModel] = []
    @Published var backgroundImageUrl = ""
    @Published var isLoading = false
    @Published var isSuccess = false

    @Injected(\.surveyQuestionViewModel) private var sharedViewModel
    private var cancellables = Set<AnyCancellable>()

    init(survey: Survey) {
        createPublisher(for: sharedViewModel.viewSateNative)
            .catch { _ -> Just<SurveyQuestionViewState> in
                let viewState = SurveyQuestionViewState()
                return Just(viewState)
            }
            .receive(on: DispatchQueue.main)
            .sink { [weak self] value in
                self?.updateState(value)
            }
            .store(in: &cancellables)

        sharedViewModel.updateStateWith(survey: survey)
    }

    func submitAnswer() {
        sharedViewModel.submitAnswer(questions: questions)
    }

    private func updateState(_ viewState: SurveyQuestionViewState) {
        questions = viewState.questions
        backgroundImageUrl = viewState.backgroundImageUrl
        isLoading = viewState.isLoading
        isSuccess = viewState.isSuccess
    }
}
