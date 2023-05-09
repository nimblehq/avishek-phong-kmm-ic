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
            imageUrl: "https://dhdbhh0jsld0o.cloudfront.net/m/1ea51560991bcb7d00d0_"
        ),
        SurveyContentView.UIModel(
            id: 0,
            title: "Career development and training",
            description: "We would like to know what are your goals ans skill you wanted to develop",
            imageUrl: "https://dhdbhh0jsld0o.cloudfront.net/m/6ea42840403875928db3_"
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
