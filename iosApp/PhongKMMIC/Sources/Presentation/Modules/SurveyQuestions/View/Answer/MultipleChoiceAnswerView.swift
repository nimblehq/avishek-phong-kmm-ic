//
//  MultipleChoiceAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct MultipleChoiceAnswerView: View {

    @State private var selectedIndex: Int?
    @ObservedObject var question: QuestionUiModel

    var body: some View {
        VStack {
            ForEach(0 ..< question.answers.count, id: \.self) { index in
                let answer = question.answers[safe: index]?.text ?? ""
                Button {
                    selectedIndex = index
                    guard let id = question.answers[safe: index]?.id else { return }
                    question.userInputs = [AnswerInput(id: id, content: nil)]
                } label: {
                    HStack {
                        Text(answer)
                            .lineLimit(1)
                            .font(getFontForAnswer(at: index))
                            .foregroundColor(getTextColorForAnswer(at: index))
                        Spacer()
                        getIconForAnswer(at: index)
                    }
                }
                .frame(height: 56.0)
                if index != question.answers.count - 1 {
                    Divider()
                        .frame(minHeight: 0.5)
                        .background(Color.white)
                }
            }
        }
        .padding(.horizontal, 60.0)
    }

    init(question: QuestionUiModel) {
        self.question = question
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
