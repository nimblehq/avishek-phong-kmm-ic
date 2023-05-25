//
//  NpsAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct NpsAnswerView: View {

    @State private var selectedIndex: Int?
    @ObservedObject var question: QuestionUiModel

    private let numberOfAnswers: Int

    var body: some View {
        VStack(spacing: 16.0) {
            answersView
            legendView
        }
    }

    private var answersView: some View {
        HStack(spacing: 0.0) {
            ForEach(0 ..< numberOfAnswers, id: \.self) { index in
                Button {
                    selectedIndex = index
                    guard let id = question.answers[safe: index]?.id else { return }
                    question.userInputs = [AnswerInput(id: id, content: nil)]
                } label: {
                    Text("\(index + 1)")
                        .font(getFontForAnswer(at: index))
                        .foregroundColor(getTextColorForAnswer(at: index))
                }
                .frame(width: 34.0)
                if index != numberOfAnswers - 1 {
                    Divider()
                        .frame(minWidth: 0.5)
                        .background(Color.white)
                }
            }
        }
        .frame(height: 56.0)
        .cornerRadius(10.0)
        .overlay(RoundedRectangle(cornerRadius: 10.0).stroke(.white, lineWidth: 1.0))
//        .padding(.horizontal, 20.0)
    }

    private var legendView: some View {
        HStack {
            let leftRangeAlpha = (selectedIndex ?? -1 < numberOfAnswers / 2) ? 1.0 : 0.5
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

    init(question: QuestionUiModel) {
        self.question = question
        numberOfAnswers = question.answers.count
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
