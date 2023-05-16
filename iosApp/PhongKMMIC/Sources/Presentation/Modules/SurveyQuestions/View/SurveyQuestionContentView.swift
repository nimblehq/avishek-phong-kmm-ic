//
//  SurveyQuestionContentVieww.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 15/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI


struct SurveyQuestionContentView: View {

    var body: some View {
        VStack {
            Text("1/5")
                .font(.boldMedium)
                .foregroundColor(Color.white.opacity(0.5))
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.bottom, 8.0)

            Text("How did WFH change your productivity?")
                .font(.boldTitle)
                .foregroundColor(Color.white)
                .frame(maxWidth: .infinity, alignment: .leading)
            Spacer()
            // TODO: Replace with correct answer type based on a response from the API
            EmojiAnswerView(type: .like, count: 5)
            Spacer()
        }
    }
}
