//
//  AppDelegate.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 23/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import UIKit

final class AppDelegate: NSObject, UIApplicationDelegate {

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        #if DEBUG
            UITestHelper.shared.speedUpUITestAnimation()
            UITestHelper.shared.clearKeychainUITest()
        #endif
        return true
    }
}
