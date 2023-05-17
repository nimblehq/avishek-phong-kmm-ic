//
//  AppCoordinator.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 20/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import FlowStacks
import SwiftUI

struct AppCoordinator: View {

    @StateObject var navigator = Navigator()

    var body: some View {
        Router($navigator.routes) { screen, _ in
            switch screen {
            case .splash:
                SplashView()
            case .login:
                LogInView()
            case .home:
                HomeView()
            case .surveyDetail:
                SurveyDetailView()
            }
        }
        .environmentObject(navigator)
    }
}
