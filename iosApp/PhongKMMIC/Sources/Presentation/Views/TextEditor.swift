//
//  TextEditor.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct TextEditor: View {

    let placeholder: String?

    @Binding var text: String

    var body: some View {
        ZStack(alignment: .topLeading) {
            if text.isEmpty {
                Text(placeholder ?? "")
                    .foregroundColor(Color.primary.opacity(0.25))
                    .padding() // Padding for matching it's content and placeholder
                    .padding(.leading, 2.0)
                    .padding(.top, 4.0)
            }
            SwiftUI.TextEditor(text: $text)
                .scrollContentBackground(.hidden)
                .primaryTextField()
        }
        .onAppear {
            UITextView.appearance().backgroundColor = .clear
        }
        .onDisappear {
            UITextView.appearance().backgroundColor = nil
        }
    }
}
