//
//  PrimaryTextFieldModifier.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 20/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct PrimaryTextFieldModifier: ViewModifier {

    func body(content: Content) -> some View {
        ZStack {
            content
                .font(.regularBody)
                .foregroundColor(Color.white)
                .tint(Color.white.opacity(0.5))
                .padding()
                .background(Color.white.opacity(0.18))
                .cornerRadius(10.0)
                .layoutPriority(2.0)
        }
    }
}

extension View {

    func primaryTextField() -> some View {
        modifier(PrimaryTextFieldModifier())
    }
}
