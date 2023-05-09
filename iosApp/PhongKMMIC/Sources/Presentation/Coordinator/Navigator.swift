//
//  Navigator.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 24/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import FlowStacks
import SwiftUI

final class Navigator: ObservableObject {

    @Published var routes: Routes<Screen> = [.root(.home, embedInNavigationView: true)]

    func showScreen(screen: Screen, with transition: Transition) {
        switch transition {
        case .root:
            routes = [.root(screen, embedInNavigationView: true)]
        case .push:
            routes.push(screen)
        case .presentSheet:
            routes.presentSheet(screen)
        case .presentCover:
            routes.presentCover(screen, embedInNavigationView: true)
        }
    }

    func goBack() {
        routes.goBack()
    }

    func goBackToRoot() {
        routes.goBackToRoot()
    }

    func pop() {
        routes.pop()
    }

    func dismiss() {
        routes.dismiss()
    }
}
