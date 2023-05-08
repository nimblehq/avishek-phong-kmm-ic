//
//  HomeView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 04/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct HomeView: View {

    @State private var isLoading = true

    private let dummySurveys = [
        SurveyContentView.UIModel(
            id: 0,
            title: "Career training and development",
            description: "We would like to know what are your goals ans skill you wanted to develop",
            image: R.image.background.image
        ),
        SurveyContentView.UIModel(
            id: 0,
            title: "Career development and training",
            description: "We would like to know what are your goals ans skill you wanted to develop",
            image: R.image.backgroundBlur.image
        )
    ]

    var body: some View {
        GeometryReader { geometryReader in
            ZStack {
                if isLoading {
                    // TODO: Handle loading animation here
                    EmptyView()
                } else {
                    SurveyContentView(uiModels: dummySurveys)
                    HomeHeaderView()
                        .frame(
                            width: geometryReader.size.width,
                            height: geometryReader.size.height,
                            alignment: .top
                        )
                }
            }
            .onAppear {
                Timer.scheduledTimer(withTimeInterval: 2.0, repeats: false) { _ in
                    isLoading.toggle()
                }
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
