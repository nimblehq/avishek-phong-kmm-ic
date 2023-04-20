//
//  PrimaryButton.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 20/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct PrimaryButton: ViewModifier {

    func body(content: Content) -> some View {
        content
            .font(.boldBody)
            .padding()
            .background(Color.white)
            .foregroundColor(Color.black)
            .cornerRadius(10.0)
    }
}

extension View {

    func primaryButton() -> some View {
        modifier(PrimaryButton())
    }
}
