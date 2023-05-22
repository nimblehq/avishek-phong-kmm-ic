//
//  SurveyContentVIew.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 07/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct SurveyContentView: View {

    @Binding var uiModels: [SurveyUiModel]
    @Binding var currentIndex: Int
    @State private var currentVisibility: Double = 1.0

    @DragGestureVelocity private var velocity: CGVector

    private var draggingSpeed: Double = 700.0
    private var draggingLength: Double = 160.0
    private var draggingVisibilityMultiplier: Double = 1.2

    private var didTapNextButtonHandler: ((String) -> Void)?

    var body: some View {
        GeometryReader { geometryReader in
            VStack {
                PageViewIndicator(numberOfPage: uiModels.count, currentPage: $currentIndex)
                    .frame(width: geometryReader.size.width - 40.0, alignment: .leading)
                    .padding(.bottom, 20.0)
                    .accessibility(.home(.pageIndicator))
                titleView
                descriptionAndNextButtonView
            }
            .padding(.horizontal, 20.0)
            .frame(
                width: geometryReader.size.width,
                height: geometryReader.size.height,
                alignment: .bottom
            )
            .background(content: {
                if let imageUrl = uiModels[safe: currentIndex]?.largeImageUrl {
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
                    let turningVisibilityPercentage =
                        draggingLength * draggingVisibilityMultiplier / abs(value.translation.width)
                    currentVisibility = max(0.0, turningVisibilityPercentage)
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

    var titleView: some View {
        Text(uiModels[safe: currentIndex]?.title ?? "")
            .foregroundColor(.white)
            .font(.boldTitle)
            .frame(maxWidth: .infinity, alignment: .leading)
            .multilineTextAlignment(.leading)
            .lineLimit(2)
            .accessibility(.home(.surveyTitleLabel))
    }

    var descriptionAndNextButtonView: some View {
        HStack {
            Text(uiModels[safe: currentIndex]?.description_ ?? "")
                .foregroundColor(.white.opacity(0.7))
                .font(.regularBody)
                .lineLimit(2)
                .accessibility(.home(.surveyDescriptionLabel))
            Spacer()
            Button {
                guard let surveyId = uiModels[safe: currentIndex]?.id else { return }
                didTapNextButtonHandler?(surveyId)
            } label: {
                R.image.rightChevron.image
                    .frame(width: 56.0, height: 56.0)
                    .background(Color.white)
                    .clipShape(Circle())
            }
            .accessibility(.home(.nextButton))
        }
    }

    init(
        uiModels: Binding<[SurveyUiModel]>,
        currentIndex: Binding<Int>,
        didTapNextButtonHandler: ((String) -> Void)?
    ) {
        _uiModels = uiModels
        _currentIndex = currentIndex
        self.didTapNextButtonHandler = didTapNextButtonHandler
    }

    private func setCurrentPageToNextPage() {
        currentIndex = min(uiModels.count - 1, currentIndex + 1)
    }

    private func setCurrentPageToPreviousPage() {
        currentIndex = max(0, currentIndex - 1)
    }
}
