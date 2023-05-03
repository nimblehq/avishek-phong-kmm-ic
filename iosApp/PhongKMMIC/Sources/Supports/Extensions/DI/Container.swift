//
//  Container.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 24/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Factory
import shared

extension Container {

    var splashViewModel: Factory<SplashViewModel> {
        Factory(self) { KoinApplication.inject(\.splashViewModel) }
    }
}
