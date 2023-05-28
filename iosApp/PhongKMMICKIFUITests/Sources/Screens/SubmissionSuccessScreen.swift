//
//  SubmissionSuccessScreen.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 25/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

final class SubmissionSuccessScreen: GenericScreen {

    func waitForAppearance() {
        tester().waitForView(withAccessibilityIdentifier: ViewId.submissionSuccess(.lottieAnimation)())
        tester().waitForView(withAccessibilityIdentifier: ViewId.submissionSuccess(.title)())
    }
}
