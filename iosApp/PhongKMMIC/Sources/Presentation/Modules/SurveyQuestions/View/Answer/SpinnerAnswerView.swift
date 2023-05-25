//
//  SpinnerAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct SpinnerAnswerView: View {

    @ObservedObject var question: QuestionUiModel

    private var selection: Binding<String> {
        Binding<String>(
            get: { question.userInputs.first?.id ?? "" },
            set: {
                question.userInputs = [AnswerInput(id: $0, content: nil)]
            }
        )
    }

    private lazy var ids: [String] = question.answers.map { $0.id }

    var body: some View {
        Picker(String(describing: Self.self), selection: selection) {
            ForEach(0 ..< question.answers.count, id: \.self) { index in
                let answer = question.answers[safe: index]?.text ?? ""
                Text(answer)
                    .font(getFontForAnswer(at: index))
                    .foregroundColor(Color.white)
                    .tag(question.answers[safe: index]?.id ?? "")
                if index < question.answers.count - 1 {
                    Divider()
                        .frame(minHeight: 0.5)
                        .background(Color.white)
                }
            }
        }
        .pickerStyle(.wheel)
        .padding(20.0)
    }

    init(question: QuestionUiModel) {
        self.question = question
        question.userInputs.insert(AnswerInput(id: question.answers.first?.id ?? "", content: nil))
    }

    private func getFontForAnswer(at index: Int) -> Font {
        if selection.wrappedValue == question.answers[safe: index]?.id {
            return .boldLarge
        }
        return .regularLarge
    }
}
