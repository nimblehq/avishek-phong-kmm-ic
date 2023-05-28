//
//  SurveyQuestionScreen.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 22/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

final class SurveyQuestionScreen: GenericScreen {

    func waitForAppearance() {
        tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.backgroundImage)())
        tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.answerContent)())
        tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.questionTitle)())
        tester().waitForView(withAccessibilityIdentifier: ViewId.surveyQuestion(.nextButton)())
    }

    func tapNextButton() {
        tester().tapView(withAccessibilityIdentifier: ViewId.surveyQuestion(.nextButton)())
    }

    func tapSubmitButton() {
        tester().tapView(withAccessibilityIdentifier: ViewId.surveyQuestion(.submitButton)())
    }
}
