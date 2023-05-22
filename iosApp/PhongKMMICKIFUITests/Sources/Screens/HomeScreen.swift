//
//  HomeScreen.swift
//  PhongKMMICKIFUITests
//
//  Created by Phong Vo on 12/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Foundation

final class HomeScreen: GenericScreen {

    func waitForAppearance() {
        tester()
            .usingTimeout(20.0)
            .waitForView(withAccessibilityIdentifier: ViewId.home(.userAvatar)())
    }

    func swipeRight() {
        tester().swipeView(
            withAccessibilityIdentifier: ViewId.home(.contentView)(),
            in: .left
        )
    }
}
