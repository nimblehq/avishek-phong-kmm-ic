//
//  LogInSpec.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 26/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

final class LogInSpec: QuickSpec {

    override func spec() {

        describe("an Login screen") {

            describe("its open") {

                it("it shows its ui components") {
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.logIn(.emailField)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.logIn(.passwordField)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.logIn(.logInButton)())
                }
            }
        }
    }
}
