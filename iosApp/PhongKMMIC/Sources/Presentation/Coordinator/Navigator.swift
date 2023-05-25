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

    @Published var routes: Routes<Screen> = [.root(.splash, embedInNavigationView: true)]

    func showScreen(screen: Screen, with transition: Transition, isAnimated: Bool = true) {
        switch transition {
        case .root:
            routes = [.root(screen, embedInNavigationView: true)]
        case .push:
            push(screen: screen, isAnimated: isAnimated)
        case .presentSheet:
            routes.presentSheet(screen)
        case .presentCover:
            presentCover(screen: screen, isAnimated: isAnimated)
        }
    }

    func goBack() {
        routes.goBack()
    }

    func goBackToRoot(isAnimated: Bool = true) {
        if isAnimated {
            routes.goBackToRoot()
        } else {
            withoutAnimation {
                routes.goBackToRoot()
            }
        }
    }

    func pop() {
        routes.pop()
    }

    func dismiss() {
        routes.dismiss()
    }

    private func push(screen: Screen, isAnimated: Bool) {
        if isAnimated {
            routes.push(screen)
        } else {
            withoutAnimation {
                routes.push(screen)
            }
        }
    }

    private func presentCover(screen: Screen, isAnimated: Bool) {
        if isAnimated {
            routes.presentCover(screen, embedInNavigationView: true)
        } else {
            withoutAnimation {
                routes.presentCover(screen, embedInNavigationView: true)
            }
        }
    }

    func steps(routes: (inout Routes<Screen>) -> Void) {
        RouteSteps.withDelaysIfUnsupported(self, \.routes) { routes(&$0) }
    }
}

extension Navigator {

    func withoutAnimation(action: () -> Void) {
        var transaction = Transaction()
        transaction.disablesAnimations = true
        withTransaction(transaction) {
            action()
        }
    }
}
