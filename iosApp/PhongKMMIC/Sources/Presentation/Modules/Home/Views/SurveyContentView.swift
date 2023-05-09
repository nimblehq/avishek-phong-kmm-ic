//
//  SurveyContentVIew.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 07/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SurveyContentView: View {

    var uiModels: [UIModel]
    @State private var currentPage = 0
    @State private var nextPage = 0
    @State private var currentVisibility: Double = 1.0

    @DragGestureVelocity private var velocity: CGVector

    private var draggingSpeed: Double = 700.0
    private var draggingLength: Double = 160.0
    private var draggingVisibilityMultiplier: Double = 1.2

    var body: some View {
        // swiftlint:disable:next closure_body_length
        GeometryReader { geometryReader in
            VStack {
                PageViewIndicator(numberOfPage: uiModels.count, currentPage: $currentPage)
                    .frame(width: geometryReader.size.width - 40.0, alignment: .leading)
                    .padding([.bottom], 20.0)
                Text(uiModels[safe: currentPage]?.title ?? "")
                    .foregroundColor(.white)
                    .font(.boldTitle)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .multilineTextAlignment(.leading)
                    .lineLimit(2)
                HStack {
                    Text(uiModels[safe: currentPage]?.description ?? "")
                        .foregroundColor(.white.opacity(0.7))
                        .font(.regularBody)
                        .lineLimit(2)
                    Spacer()
                    Button {
                        // TODO: Handle button taps
                        print("Next button was tapped")
                    } label: {
                        R.image.rightChevron.image
                            .frame(width: 56.0, height: 56.0)
                            .background(Color.white)
                            .clipShape(Circle())
                    }
                }
            }
            .padding(.horizontal, 20.0)
            .frame(
                width: geometryReader.size.width,
                height: geometryReader.size.height,
                alignment: .bottom
            )
            .background(content: {
                if let imageUrl = uiModels[safe: currentPage]?.imageUrl {
                    Image.url(imageUrl)
                        .resizable()
                        .scaledToFill()
                        .clipped()
                        .ignoresSafeArea(.all, edges: .all)
                } else {
                    EmptyView()
                }
            })
            .opacity(currentVisibility)
        }
        .gesture(
            DragGesture(minimumDistance: 5.0, coordinateSpace: .local)
                .onChanged { value in
                    switch value.translation.width {
                    case ...0: // Right to left
                        nextPage = min(uiModels.count - 1, currentPage + 1)
                    case 0...:
                        nextPage = max(0, currentPage - 1)
                    default:
                        return
                    }
                    let turningVisibilityPercentage =
                        draggingLength * draggingVisibilityMultiplier / abs(value.translation.width)
                    currentVisibility = max(0.0, turningVisibilityPercentage)
                    print(value.location)
                }
                .onEnded { value in
                    withAnimation(.linear(duration: 0.5)) {
                        // Fast swipe
                        if self.velocity.dx < -draggingSpeed {
                            setCurrentPageToNextPage()
                        } else if self.velocity.dx > draggingSpeed {
                            setCurrentPageToPreviousPage()
                        } else {
                            // Slow dragging
                            switch value.translation.width {
                            case ...(-draggingLength):
                                setCurrentPageToNextPage()
                            case draggingLength...:
                                setCurrentPageToPreviousPage()
                            default: break
                            }
                        }
                        currentVisibility = 1.0
                    }
                }
                .updatingVelocity($velocity)
        )
    }

    init(uiModels: [UIModel]) {
        self.uiModels = uiModels
    }

    private func setCurrentPageToNextPage() {
        currentPage = min(uiModels.count - 1, currentPage + 1)
    }

    private func setCurrentPageToPreviousPage() {
        currentPage = max(0, currentPage - 1)
    }
}

extension SurveyContentView {

    struct UIModel {

        let id: Int
        let title: String
        let description: String
        let imageUrl: String
    }
}

struct SurveyContentView_Previews: PreviewProvider {

    static let uiModels = [
        SurveyContentView.UIModel(
            id: 0,
            title: "Career training and development",
            description: "We would like to know what are your goals ans skill you wanted to develop",
            imageUrl: "https://image-1.uhdpaper.com/b/phone-4k/apple-2021-logo-4k-phone-wallpaper-2160x3840-uhdpaper.com-945.1_b.jpg"
        ),
        SurveyContentView.UIModel(
            id: 0,
            title: "Career development and training",
            description: "We would like to know what are your goals ans skill you wanted to develop",
            imageUrl: "https://image-0.uhdpaper.com/wallpaper/apple-logo-black-background-phone-wallpaper-4k-uhdpaper.com-668@0@e.jpg"
        )
    ]

    static var previews: some View {
        SurveyContentView(
            uiModels: uiModels
        )
        .preferredColorScheme(.dark)
    }
}
