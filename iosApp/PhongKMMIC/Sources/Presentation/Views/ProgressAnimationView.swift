//
//  ProgressAnimationView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 25/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct ProgressAnimationView: View {

    @Binding var isPresented: Bool

    var body: some View {
        HStack {
            VStack {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle())
            }
            .frame(
                width: 50.0,
                height: 50.0
            )
            .background(Color.white.opacity(0.3))
            .cornerRadius(8.0)
        }
        .frame(
            maxWidth: .infinity,
            maxHeight: .infinity
        )
        .background(Color.black.opacity(0.3))
        .ignoresSafeArea()
        .opacity(isPresented ? 1.0 : 0.0)
        .animation(.easeIn(duration: 0.3), value: isPresented)
    }
}

private struct ProgressAnimationViewModifier: ViewModifier {

    let isPresented: Binding<Bool>

    func body(content: Content) -> some View {
        if #available(iOS 15.0, *) {
            content.overlay {
                ProgressAnimationView(isPresented: isPresented)
            }
        } else {
            ZStack {
                content
                ProgressAnimationView(isPresented: isPresented)
            }
            .ignoresSafeArea()
        }
    }
}

extension View {

    func progressView(_ isPresented: Binding<Bool>) -> some View {
        modifier(ProgressAnimationViewModifier(isPresented: isPresented))
    }
}
