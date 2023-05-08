//
//  PrimaryTextFieldModifier.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 20/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct PrimaryTextFieldModifier: ViewModifier {

    @Binding var hasError: Bool

    func body(content: Content) -> some View {
        ZStack {
            content
                .font(.regularBody)
                .accentColor(Color.white)
                .padding()
                .background(Color.white.opacity(0.18))
                .cornerRadius(10.0)
                .layoutPriority(2.0)
            if hasError {
                RoundedRectangle(cornerRadius: 10.0)
                    .stroke(Color.red, lineWidth: hasError ? 3.0 : 0.0)
            }
        }
    }
}

extension View {

    func primaryTextField(hasError: Binding<Bool> = .constant(false)) -> some View {
        modifier(PrimaryTextFieldModifier(hasError: hasError))
    }
}
