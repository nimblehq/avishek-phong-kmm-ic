//
//  SingleFormAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct SingleFormAnswerView: View {

    @ObservedObject var question: QuestionUiModel

    private var input: Binding<String> {
        Binding<String>(
            get: { question.userInputs.first?.content ?? "" },
            set: {
                let id = question.answers.first?.id ?? ""
                question.userInputs = [AnswerInput(id: id, content: $0)]
            }
        )
    }

    var body: some View {
        TextEditor(placeholder: question.answers.first?.placeholder ?? "", text: input)
            .frame(height: 170.0)
    }
}
