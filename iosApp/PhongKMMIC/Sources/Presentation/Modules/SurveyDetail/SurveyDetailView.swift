//
//  SurveyDetailView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 15/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SurveyDetailView: View {

    @EnvironmentObject private var navigator: Navigator

    @State private var backgroundScale = 1.0

    var body: some View {
        ZStack {
            GeometryReader { geometryReader in
                Image.url("https://dhdbhh0jsld0o.cloudfront.net/m/4670c0dca0ed82de564a_l")
                    .resizable()
                    .scaledToFill()
                    .frame(width: geometryReader.size.width, height: geometryReader.size.height)
                    .scaleEffect(backgroundScale, anchor: .topTrailing)
                BlackGradientOverlay()
            }
            .ignoresSafeArea()

            VStack(alignment: .leading) {
                Text("Working from home Check-in")
                    .font(.boldTitle)
                    .foregroundColor(Color.white)

                Text("We would like to know how you feel about our work from home (WFH) experience")
                    .font(.regularBody)
                    .foregroundColor(Color.white.opacity(0.7))
                    .padding(.top, 16.0)

                Spacer()
                HStack {
                    Spacer()
                    Button {
                        print("Start survey was tapped")
                    } label: {
                        Text(R.string.localizable.surveyDetailStartSurvey())
                            .frame(alignment: .center)
                            .font(.boldBody)
                            .padding(.horizontal, 21.0)
                    }
                    .frame(height: 56.0)
                    .background(Color.white)
                    .foregroundColor(Color.black)
                    .cornerRadius(10.0)
                }
            }
            .padding(.top, 26.0)
            .padding(.horizontal, 20.0)
        }
        .toolbar {
            ToolbarItem(placement: .navigationBarLeading) {
                Button {
                    didTapBackButton()
                } label: {
                    R.image.whiteLeftChevron.image
                }
            }
        }
        .navigationBarBackButtonHidden(true)
        .onLoad {
            DispatchQueue.main.async {
                withAnimation(.easeIn(duration: 0.4)) {
                    backgroundScale = 1.4
                }
            }
        }
    }

    private func didTapBackButton() {
        withAnimation(.easeIn(duration: 0.4)) {
            backgroundScale = 1.0
        }
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.4) {
            self.navigator.goBackToRoot(isAnimated: false)
        }
    }
}
