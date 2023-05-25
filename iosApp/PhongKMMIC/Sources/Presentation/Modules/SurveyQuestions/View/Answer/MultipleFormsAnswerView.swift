//
//  MultipleFormsAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 17/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct MultipleFormsAnswerView: View {

    @ObservedObject var question: QuestionUiModel
    private let numberOfAnswers: Int

    // TODO: relace this with actual logic for binding user's inputs
    @State private var values = ""

    var body: some View {
        VStack(spacing: 16.0) {
            ForEach(0 ..< question.answers.count, id: \.self) { index in
                TextField(question.answers[safe: index]?.placeholder, text: content(of: index))
                    .primaryTextField()
                    .padding(.horizontal, 12.0)
            }
        }
    }

    init(question: QuestionUiModel) {
        self.question = question
        numberOfAnswers = question.answers.count
    }

    func content(of index: Int) -> Binding<String> {
        return Binding<String>(
            get: {
                let currentInput = getCurrentInput(index)
                return currentInput?.content ?? ""

            },
            set: {
                let answerId = question.answers[safe: index]?.id ?? ""
                let currentInput = getCurrentInput(index)
                if let currentInput {
                    question.userInputs.remove(currentInput)
                }
                question.userInputs.insert(AnswerInput(id: answerId, content: $0))
            }
        )
    }

    private func getCurrentInput(_ index: Int) -> AnswerInput? {
        let answerId = question.answers[safe: index]?.id ?? ""
        return question.userInputs.first(where: { $0.id == answerId })
    }
}
