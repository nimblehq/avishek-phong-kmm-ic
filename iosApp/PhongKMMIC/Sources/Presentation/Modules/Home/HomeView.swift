//
//  HomeView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 04/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct HomeView: View {

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
                    SurveyContentView(uiModels: $viewModel.surveys, currentIndex: $selectedIndex)
                        .onChange(of: selectedIndex) {
                            viewModel.loadMoreSurvey(selectedIndex: $0)
                        }
                        .accessibility(.home(.contentView))
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
}

struct HomeView_Previews: PreviewProvider {

    static var previews: some View {
        HomeView()
            .preferredColorScheme(.dark)
    }
}
