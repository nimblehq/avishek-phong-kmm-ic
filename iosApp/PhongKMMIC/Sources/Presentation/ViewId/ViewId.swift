//
//  ViewId.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 21/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

enum ViewId {

    case splash(Splash)
    case logIn(LogIn)
    case general(General)
    case home(Home)

    enum General: String {

        case back = "Back"
        case backButton = "back.button"
    }

    enum Splash: String {

        case background = "splash.background"
        case nimbleLogo = "splash.nimbleLogo"
    }

    enum LogIn: String {

        case emailField = "logIn.emailField"
        case passwordField = "logIn.passwordField"
        case logInButton = "logIn.logInButton"
    }

    enum Home: String {

        case userAvatar = "home.userAvatar"
        case dateTimeLabel = "home.dateTimeLabel"
        case todayLabel = "home.todayLabel"
        case pageIndicator = "home.pageIndicator"
        case surveyTitleLabel = "home.surveyTitleLabel"
        case surveyDescriptionLabel = "home.descriptionLabel"
        case nextButton = "home.nextButton"
        case contentView = "home.contentView"
    }

    func callAsFunction() -> String {
        switch self {
        case let .general(general):
            return general.rawValue
        case let .splash(splash):
            return splash.rawValue
        case let .logIn(login):
            return login.rawValue
        case let .home(home):
            return home.rawValue
        }
    }
}

extension View {

    func accessibility(_ viewId: ViewId) -> some View {
        accessibilityIdentifier(viewId())
    }
}
