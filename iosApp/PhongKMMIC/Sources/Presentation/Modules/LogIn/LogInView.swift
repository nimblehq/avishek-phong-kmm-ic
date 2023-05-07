//
//  LogInView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 19/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct LogInView: View {

    @State private var isLoaded = false
    @State private var email = ""
    @State private var password = ""

    @StateObject private var viewModel = LogInCombineViewModel()

    var body: some View {
        ZStack {
            R.image.backgroundBlur.image
                .resizable()
                .aspectRatio(contentMode: .fill)

            VStack {
                R.image.logoWhite.image
                    .aspectRatio(contentMode: .fill)
                    .frame(maxWidth: .infinity)
                    .offset(y: isLoaded ? 0.0 : 200.0)
                    .onAppear {
                        withAnimation(.easeOut(duration: 0.5)) {
                            isLoaded.toggle()
                        }
                    }

                Spacer().frame(maxHeight: 70.0)

                if isLoaded {
                    emailField
                    passwordField
                    loginButton
                }
            }
            .padding(.horizontal, 24.0)
        }
        .ignoresSafeArea()
        .progressView($viewModel.isLoading)
    }

    var emailField: some View {
        TextField(
            R.string.localizable.authenticationFieldsEmail(),
            text: $viewModel.email
        )
        .autocapitalization(.none)
        .disableAutocorrection(true)
        .keyboardType(.emailAddress)
        .primaryTextField(error: $viewModel.isInvalidEmail)
        .accessibility(.logIn(.emailField))
    }

    var passwordField: some View {
        HStack {
            SecureField(
                R.string.localizable.authenticationFieldsPassword(),
                text: $viewModel.password
            )
        }
        .primaryTextField(error: $viewModel.isInvalidPassword)
        .accessibility(.logIn(.passwordField))
    }

    var loginButton: some View {
        Button(R.string.localizable.authenticationButtonLogin()) {
            viewModel.login()
        }
        .frame(maxWidth: .infinity)
        .primaryButton()
        .accessibility(.logIn(.logInButton))
    }
}

struct LogInView_Previews: PreviewProvider {

    static var previews: some View {
        LogInView()
    }
}
