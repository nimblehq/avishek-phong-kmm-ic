//
//  HomeView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 04/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

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
            .onAppear {
                viewModel.fetchData()
            }
        }
    }

    private var surveyContentView: some View {
        SurveyContentView(
            uiModels: $viewModel.surveys,
            currentIndex: $selectedIndex,
            didTapNextButtonHandler: { openSurveyDetail(surveyId: $0) }
        )
        .onChange(of: selectedIndex) {
            viewModel.loadMoreSurvey(selectedIndex: $0)
        }
        .accessibility(.home(.contentView))
    }

    private func openSurveyDetail(surveyId: String) {
        navigator.showScreen(screen: .surveyDetail, with: .push)
    }
}

struct HomeView_Previews: PreviewProvider {

    static var previews: some View {
        HomeView()
            .preferredColorScheme(.dark)
    }
}
