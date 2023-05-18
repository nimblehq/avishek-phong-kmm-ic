//
//  NpsAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct NpsAnswerView: View {

    @State private var selectedIndex: Int?

    var body: some View {
        VStack(spacing: 16.0) {
            answersView
            legendView
        }
    }

    private var answersView: some View {
        HStack(spacing: 0.0) {
            ForEach(0 ..< 10, id: \.self) { index in
                Button {
                    selectedIndex = index
                    print("selected answer at: \(index)")
                } label: {
                    Text("\(index + 1)")
                        .font(getFontForAnswer(at: index))
                        .foregroundColor(getTextColorForAnswer(at: index))
                }
                .frame(width: 34.0)
                if index != 10 - 1 {
                    Divider()
                        .frame(minWidth: 0.5)
                        .background(Color.white)
                }
            }
        }
        .frame(height: 56.0)
        .cornerRadius(10.0)
        .overlay(RoundedRectangle(cornerRadius: 10.0).stroke(.white, lineWidth: 1.0))
        .padding(.horizontal, 20.0)
    }

    private var legendView: some View {
        HStack {
            let leftRangeAlpha = (selectedIndex ?? -1 < 10 / 2) ? 1.0 : 0.5
            let isInvalidIndex = selectedIndex ?? -1 < 0

            Text(R.string.localizable.surveyQuestionsNpsUnlike())
                .foregroundColor(Color.white)
                .opacity(isInvalidIndex ? 0.5 : leftRangeAlpha)
                .font(.boldBody)
            Spacer()
            Text(R.string.localizable.surveyQuestionsNpsLike())
                .foregroundColor(Color.white)
                .opacity(isInvalidIndex ? 0.5 : 1.5 - leftRangeAlpha)
                .font(.boldBody)
        }
        .frame(width: 345.5)
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
}
