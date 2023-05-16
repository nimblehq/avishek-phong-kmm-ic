//
//  SurveyQuestionsView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 15/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI
import SwiftUIPager

struct SurveyQuestionsView: View {

    @EnvironmentObject private var navigator: Navigator

    @StateObject private var page: Page = .first()

    var body: some View {
        ZStack {
            GeometryReader { geometryReader in
                Image.url("https://dhdbhh0jsld0o.cloudfront.net/m/4670c0dca0ed82de564a_l")
                    .resizable()
                    .scaledToFill()
                    .frame(width: geometryReader.size.width, height: geometryReader.size.height)
                BlackGradientOverlay()
            }
            .ignoresSafeArea()

            Pager(page: page, data: 0 ..< 6, id: \.self) { _ in
                SurveyQuestionContentView()
                    .padding(.top, 26.0)
                    .padding(.horizontal, 20.0)
                    .padding(.bottom, 56.0)
            }
            .disableDragging()

            VStack(alignment: .leading) {
                Spacer()
                HStack {
                    Spacer()
                    Button {
                        print("next button is tapped")
                    } label: {
                        R.image.rightChevron.image
                            .frame(width: 56.0, height: 56.0)
                            .background(Color.white)
                            .clipShape(Circle())
                    }
                }
            }
            .padding(.horizontal, 20.0)
        }
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    didTapCloseButton()
                } label: {
                    R.image.close.image
                }
            }
        }
        .navigationBarBackButtonHidden(true)
    }

    private func didTapCloseButton() {
        navigator.goBackToRoot()
    }
}
