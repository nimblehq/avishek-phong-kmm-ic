//
//  SurveyQuestionsSpec.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 22/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Nimble
import Quick

final class SurveyQuestionsSpec: QuickSpec {

    override func spec() {

        var logInScreen: LogInScreen!
        var homeScreen: HomeScreen!
        var surveyDetailScreen: SurveyDetailScreen!
        var surveyQuestionScreen: SurveyQuestionScreen!
        var submissionSuccessScreen: SubmissionSuccessScreen!

        // swiftlint:disable:next closure_body_length
        describe("an Survey Detail screen") {

            beforeEach {
                logInScreen = LogInScreen(self)
                homeScreen = HomeScreen(self)
                surveyDetailScreen = SurveyDetailScreen(self)
                surveyQuestionScreen = SurveyQuestionScreen(self)
                submissionSuccessScreen = SubmissionSuccessScreen(self)
            }

            context("when the user start a survey") {

                beforeEach {
                    logInScreen.loginIfNeeded()
                    homeScreen.waitForAppearance()
                    homeScreen.tapNextButton()
                    surveyDetailScreen.waitForAppearance()
                    surveyDetailScreen.tapStartButton()
                }

                it("shows its ui components") {
                    surveyQuestionScreen.waitForAppearance()
                }
            }

            context("when the user start a survey") {

                beforeEach {
                    logInScreen.loginIfNeeded()
                    homeScreen.waitForAppearance()
                    homeScreen.tapNextButton()
                    surveyDetailScreen.waitForAppearance()
                    surveyDetailScreen.tapStartButton()
                    surveyQuestionScreen.waitForAppearance()
                    while self.tester()
                        .waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.nextButton)()) == nil {
                        surveyDetailScreen.tapStartButton()
                    }
                }

                it("shows its ui components") {
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.backgroundImage)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.answerContent)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.questionTitle)())
                    self.tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.submitButton)())
                }
            }

            context("when the user submit their answer") {

                beforeEach {
                    logInScreen.loginIfNeeded()
                    homeScreen.waitForAppearance()
                    homeScreen.tapNextButton()
                    surveyDetailScreen.waitForAppearance()
                    surveyDetailScreen.tapStartButton()
                    surveyQuestionScreen.waitForAppearance()
                    while self.tester()
                        .waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.nextButton)()) == nil {
                        surveyDetailScreen.tapStartButton()
                    }
                    surveyQuestionScreen.tapSubmitButton()
                }

                afterEach {
                    homeScreen.waitForAppearance()
                }

                it("shows thank you screen") {
                    submissionSuccessScreen.waitForAppearance()
                }
            }
        }
    }
}
