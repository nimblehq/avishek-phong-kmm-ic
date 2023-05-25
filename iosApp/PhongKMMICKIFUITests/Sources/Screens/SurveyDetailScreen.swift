//
//  SurveyDetailScreen.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 18/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

final class SurveyDetailScreen: GenericScreen {

    func waitForAppearance() {
        tester().waitForView(withAccessibilityIdentifier: ViewId.surveyDetail(.backgroundImage)())
    }

    func tapStartButton() {
        tester().tapView(withAccessibilityIdentifier: ViewId.surveyDetail(.startButton)())
    }
}
