//
//  SurveyDetailSpec.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 18/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SurveyDetailSpec: QuickSpec {

    override func spec() {

        var logInScreen: LogInScreen!
        var homeScreen: HomeScreen!
        var surveyDetailScreen: SurveyDetailScreen!

        describe("an Survey Detail screen") {

            beforeEach {
                logInScreen = LogInScreen(self)
                homeScreen = HomeScreen(self)
                surveyDetailScreen = SurveyDetailScreen(self)
            }

            describe("its open") {

                beforeEach {
                    logInScreen.loginIfNeeded()
                    homeScreen.waitForAppearance()
                    homeScreen.tapNextButton()
                }

                afterEach {
                    surveyDetailScreen.pressModelBackButton()
                    homeScreen.waitForAppearance()
                }

                it("shows its ui components") {
                    surveyDetailScreen.waitForAppearance()
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.surveyDetail(.title)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.surveyDetail(.description)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.surveyDetail(.backgroundImage)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.surveyDetail(.startButton)())
                }
            }
        }
    }
}
