//
//  SurveyQuestionContentVieww.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 15/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct SurveyQuestionContentView: View {

    @ObservedObject var question: QuestionUiModel

    var body: some View {
        ScrollView {
            VStack {
                Text(question.step)
                    .font(.boldMedium)
                    .foregroundColor(Color.white.opacity(0.5))
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.bottom, 8.0)

                Text(question.questionTitle)
                    .font(.boldTitle)
                    .foregroundColor(Color.white)
                    .frame(maxWidth: .infinity, alignment: .leading)
                Spacer(minLength: 150.0)
                answerView
            }
        }
    }

    private var answerView: some View {
        VStack {
            switch question.displayType {
            case .choice:
                MultipleChoiceAnswerView(question: question)
            case .dropdown:
                SpinnerAnswerView(question: question)
            case .heart, .smiley, .star:
                EmojiAnswerView(question: question)
            case .nps:
                NpsAnswerView(question: question)
            case .textarea:
                SingleFormAnswerView(question: question)
            case .textfield:
                MultipleFormsAnswerView(question: question)
            default:
                EmptyView()
            }
        }
    }

    init(question: QuestionUiModel) {
        self.question = question
    }
}
