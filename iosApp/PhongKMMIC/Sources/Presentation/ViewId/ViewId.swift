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

    enum General: String {
        case back = "Back"
        case backButton = "back.button"
    }

    enum Splash: String {
        case background = "splash.background"
        case nimbleLogo = "splash.nimbleLogo"
    }

    enum LogIn: String {
        case view = "logIn.view"
        case emailField = "logIn.emailField"
        case passwordField = "logIn.passwordField"
        case logInButton = "logIn.logInButton"
    }

    func callAsFunction() -> String {
        switch self {
        case let .general(general):
            return general.rawValue
        case let .splash(splash):
            return splash.rawValue
        case let .logIn(login):
            return login.rawValue
        }
    }
}

extension View {

    func accessibility(_ viewId: ViewId) -> some View {
        accessibilityIdentifier(viewId())
    }
}
