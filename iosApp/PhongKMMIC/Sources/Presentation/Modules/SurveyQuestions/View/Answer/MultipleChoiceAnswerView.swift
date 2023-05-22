//
//  MultipleChoiceAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct MultipleChoiceAnswerView: View {

    @State private var selectedIndex: Int?

    var body: some View {
        VStack {
            ForEach(0 ..< 10, id: \.self) { index in
                Button {
                    selectedIndex = index
                    // TODO: replace with actual logic when a user slected an answer
                    print("selected answer at: \(index)")
                } label: {
                    HStack {
                        Text("Answer number: \(index)")
                            .lineLimit(1)
                            .font(getFontForAnswer(at: index))
                            .foregroundColor(getTextColorForAnswer(at: index))
                        Spacer()
                        getIconForAnswer(at: index)
                    }
                }
                .frame(height: 56.0)
                // TODO: replace with actual count of answer
                if index != 10 - 1 {
                    Divider()
                        .frame(minHeight: 0.5)
                        .background(Color.white)
                }
            }
        }
        .padding(.horizontal, 60.0)
    }

    private func getFontForAnswer(at index: Int) -> Font {
        if index == selectedIndex {
            return .boldLarge
        }
        return .regularLarge
    }

    private func getTextColorForAnswer(at index: Int) -> Color {
        if index == selectedIndex {
            return .white
        }
        return .white.opacity(0.5)
    }

    private func getIconForAnswer(at index: Int) -> Image {
        if index == selectedIndex {
            return R.image.selectedIcon.image
        }
        return R.image.unselectedIcon.image
    }
}
