//
//  SplashSpec.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 23/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SplashSpec: QuickSpec {

    override func spec() {

        var logInScreen: LogInScreen!

        describe("a Splash screen") {

            beforeEach {
                logInScreen = LogInScreen(self)
            }

            describe("its open") {

                afterEach {
                    logInScreen.waitForAppearance()
                }

                it("has background image and nimble log") {
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.splash(.background)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.splash(.nimbleLogo)())
                }
            }
        }
    }
}
