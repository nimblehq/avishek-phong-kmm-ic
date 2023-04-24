//
//  LogInScreen.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 23/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

final class LogInScreen: GenericScreen {

    func waitForAppearance() {
        tester().waitForView(withAccessibilityIdentifier: ViewId.logIn(.view)())
    }
}
