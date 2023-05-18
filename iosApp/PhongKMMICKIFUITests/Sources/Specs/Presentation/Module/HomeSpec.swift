//
//  HomeSpec.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 12/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

final class HomeSpec: QuickSpec {

    override func spec() {

        var logInScreen: LogInScreen!
        var homeScreen: HomeScreen!

        describe("a Home screen") {

            beforeEach {
                logInScreen = LogInScreen(self)
                homeScreen = HomeScreen(self)
            }

            describe("it's open") {

                beforeEach {
                    logInScreen.system().wait(forTimeInterval: 1)
                    logInScreen.loginIfNeeded()
                    homeScreen.waitForAppearance()
                }

                context("when swipe left multitple times") {

                    beforeEach {
                        for _ in 0 ..< 5 {
                            homeScreen.swipeRight()
                        }
                    }

                    it("show new surveys") {
                        self.tester().waitForView(withAccessibilityIdentifier: ViewId.home(.contentView)())
                    }
                }
            }
        }
    }
}
