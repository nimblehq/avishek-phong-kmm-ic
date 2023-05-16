//
//  LogInScreen.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 23/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

final class LogInScreen: GenericScreen {

    func waitForAppearance() {
        tester().waitForView(withAccessibilityIdentifier: ViewId.logIn(.emailField)())
        tester().waitForView(withAccessibilityIdentifier: ViewId.logIn(.passwordField)())
        tester().waitForView(withAccessibilityIdentifier: ViewId.logIn(.logInButton)())
    }

    func loginIfNeeded() {
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.home(.contentView)()) {
            return
        }
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.splash(.background)()) {
            tester().waitForAbsenceOfView(withAccessibilityIdentifier: ViewId.splash(.background)())
        }
        if tester().tryFindingView(withAccessibilityIdentifier: ViewId.logIn(.emailField)()) {
            tester().waitForTappableView(withAccessibilityIdentifier: ViewId.logIn(.logInButton)())
            tester().tapView(withAccessibilityIdentifier: ViewId.logIn(.logInButton)())
        }
    }
}
