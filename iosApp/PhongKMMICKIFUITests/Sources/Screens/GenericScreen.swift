//
//  GenericScreen.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 21/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import KIF
import XCTest

class GenericScreen {

    weak var testCase: XCTestCase!

    init(_ testCase: XCTestCase!) {
        self.testCase = testCase
    }

    func tester(file: String = #file, _ line: Int = #line) -> KIFUITestActor {
        return testCase.tester(file: file, line)
    }

    func system(file: String = #file, _ line: Int = #line) -> KIFSystemTestActor {
        return testCase.system(file: file, line)
    }

    func pressBack() {
        tester().waitForAnimationsToFinish()
        tester().tapView(withAccessibilityLabel: ViewId.general(.back)())
    }

    func pressModelBackButton() {
        tester().tapView(withAccessibilityIdentifier: ViewId.general(.backButton)())
    }
}
