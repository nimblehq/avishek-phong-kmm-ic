//
//  HomeView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 04/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct HomeView: View {

    @EnvironmentObject private var navigator: Navigator

    @StateObject private var viewModel = HomeCombineViewModel()
    @State private var selectedIndex = 0

    var body: some View {
        GeometryReader { geometryReader in
            ZStack {
                if viewModel.isLoading {
                    SkeletonHomeView()
                        .frame(
                            width: geometryReader.size.width,
                            height: geometryReader.size.height
                        )
                } else {
                    surveyContentView
                    HomeHeaderView(
                        userAvatarUrl: $viewModel.userAvatarUrl,
                        today: $viewModel.today
                    )
                    .frame(
                        width: geometryReader.size.width,
                        height: geometryReader.size.height,
                        alignment: .top
                    )
                }
            }
            .onLoad {
                viewModel.fetchData()
            }
        }
    }

    private var surveyContentView: some View {
        SurveyContentView(
            uiModels: $viewModel.surveys,
            currentIndex: $selectedIndex,
            didTapNextButtonHandler: { openSurveyDetail(id: $0) }
        )
        .onChange(of: selectedIndex) {
            viewModel.loadMoreSurvey(selectedIndex: $0)
        }
        .accessibility(.home(.contentView))
    }

    private func openSurveyDetail(id: String) {
        guard let survey = viewModel.getSurveyWith(id: id) else { return }
        navigator.showScreen(
            screen: .surveyDetail(survey: survey),
            with: .presentCover,
            isAnimated: false
        )
    }
}
