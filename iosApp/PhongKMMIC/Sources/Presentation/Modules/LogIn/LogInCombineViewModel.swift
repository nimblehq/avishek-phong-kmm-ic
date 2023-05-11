//
//  LoginView+DataSource.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 25/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Combine
import Factory
import Foundation
import KMPNativeCoroutinesCombine
import shared

final class LogInCombineViewModel: ObservableObject {

    @Published var email = ""
    @Published var password = ""
    @Published var isLoading = false
    @Published var isInvalidEmail = false
    @Published var isInvalidPassword = false
    @Published var isLogInSuccess = false

    private var cancellables = Set<AnyCancellable>()

    @Published private(set) var viewState = LogInViewState()
    @Injected(\.logInViewModel) private var viewModel: shared.LogInViewModel

    init() {
        createPublisher(for: viewModel.viewStateNative)
            .catch { error -> Just<LogInViewState> in
                let logInViewState = LogInViewState(error: error.localizedDescription)
                return Just(logInViewState)
            }
            .receive(on: DispatchQueue.main)
            .sink { [weak self] value in
                self?.updateStates(value)
            }
            .store(in: &cancellables)
    }

    func login() {
        viewModel.logIn(email: email, password: password)
    }

    private func updateStates(_ state: LogInViewState) {
        viewState = state
        isInvalidEmail = state.isInvalidEmail
        isInvalidPassword = state.isInvalidPassword
        isLoading = state.isLoading
        isLogInSuccess = state.isSuccess
    }
}
