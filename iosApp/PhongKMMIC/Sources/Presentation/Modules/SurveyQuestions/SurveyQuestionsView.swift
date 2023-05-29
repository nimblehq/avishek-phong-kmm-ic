//
//  SurveyQuestionsView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 15/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI
import SwiftUIPager

struct SurveyQuestionsView: View {

    @EnvironmentObject private var navigator: Navigator

    @StateObject private var page: Page = .first()
    @State private var isAlertShown = false

    @ObservedObject private var viewModel: SurveyQuestionsCombineViewModel

    var body: some View {
        ZStack {
            GeometryReader { geometryReader in
                Image.url(viewModel.backgroundImageUrl)
                    .resizable()
                    .scaledToFill()
                    .frame(width: geometryReader.size.width, height: geometryReader.size.height)
                    .accessibility(.surveyQuestion(.backgroundImage))
                BlackGradientOverlay()
            }
            .ignoresSafeArea()

            Pager(page: page, data: viewModel.questions, id: \.id) { question in
                SurveyQuestionContentView(question: question)
                    .padding(.top, 26.0)
                    .padding(.horizontal, 20.0)
                    .padding(.bottom, 56.0)
                    .accessibility(.surveyQuestion(.answerContent))
            }
            .disableDragging()

            VStack(alignment: .leading) {
                let isLastPage = self.page.index == viewModel.questions.count - 1
                Spacer()
                HStack {
                    Spacer()
                    Button {
                        if isLastPage {
                            viewModel.submitAnswer()
                        } else {
                            withAnimation {
                                page.update(.next)
                            }
                        }
                    } label: {
                        if isLastPage {
                            Text(R.string.localizable.surveyQuestionsSubmit())
                                .foregroundColor(Color.black)
                                .font(.boldBody)
                                .frame(alignment: .center)
                                .padding()
                                .accessibility(.surveyQuestion(.submitButton))
                        } else {
                            R.image.rightChevron.image
                                .clipShape(Circle())
                                .accessibility(.surveyQuestion(.nextButton))
                        }
                    }
                    .frame(width: isLastPage ? nil : 56.0, height: 56.0)
                    .background(Color.white)
                    .cornerRadius(isLastPage ? 10.0 : 28.0)
                }
            }
            .padding(.horizontal, 20.0)
        }
        .onChange(of: viewModel.isSuccess) {
            if $0 {
                navigator.showScreen(screen: .thankYou, with: .push)
            }
        }
        .alert(isPresented: $isAlertShown, content: {
            Alert(
                title: Text(R.string.localizable.alertWarningTitle()),
                message: Text(R.string.localizable.surveyQuestionScreenExitAlertMessage()),
                primaryButton: .default(Text(R.string.localizable.alertYesButtonTitle())) {
                    navigator.goBackToRoot()
                },
                secondaryButton: .cancel(Text(R.string.localizable.alertCancelButtonTitle())) {}
            )
        })
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button {
                    isAlertShown = true
                } label: {
                    R.image.close.image
                }
            }
        }
        .navigationBarBackButtonHidden(true)
        .progressView($viewModel.isLoading)
    }

    init(survey: Survey) {
        viewModel = SurveyQuestionsCombineViewModel(survey: survey)
    }
}
