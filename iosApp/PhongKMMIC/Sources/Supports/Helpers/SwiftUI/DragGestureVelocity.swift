//
//  DragGestureVelocity.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 08/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

@propertyWrapper
struct DragGestureVelocity: DynamicProperty {

    @State private var previous: DragGesture.Value?
    @State private var current: DragGesture.Value?

    var projectedValue: DragGestureVelocity {
        self
    }

    var wrappedValue: CGVector {
        value
    }

    private var value: CGVector {
        guard let previous = previous,
              let current = current
        else {
            return .zero
        }

        let timeDelta = current.time.timeIntervalSince(previous.time)

        let velocityY = Double(
            current.translation.height - previous.translation.height
        ) / timeDelta

        let velocityX = Double(
            current.translation.width - previous.translation.width
        ) / timeDelta

        return CGVector(dx: velocityX, dy: velocityY)
    }

    func update(_ value: DragGesture.Value) {
        if current != nil {
            previous = current
        }
        current = value
    }

    func reset() {
        previous = nil
        current = nil
    }
}

extension Gesture where Value == DragGesture.Value {

    func updatingVelocity(_ velocity: DragGestureVelocity) -> _EndedGesture<_ChangedGesture<Self>> {
        onChanged { value in
            velocity.update(value)
        }
        .onEnded { _ in
            velocity.reset()
        }
    }
}
