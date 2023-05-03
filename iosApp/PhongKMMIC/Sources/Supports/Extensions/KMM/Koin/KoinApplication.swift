//
//  KoinApplication.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 20/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared

extension KoinApplication {

    static let shared = companion.start()

    @discardableResult
    static func start() -> KoinApplication {
        shared
    }
}

extension KoinApplication {

    static func inject<T>(_ keyPath: PartialKeyPath<Koin>) -> T {
        guard let keyPath = keyPath as? KeyPath<Koin, T> else {
            fatalError("\(T.self) is not registered with KoinApplication")
        }
        return shared.koin[keyPath: keyPath]
    }
}
