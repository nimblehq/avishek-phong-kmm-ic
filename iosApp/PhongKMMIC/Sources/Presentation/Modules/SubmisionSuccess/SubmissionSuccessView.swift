//
//  SubmissionSuccessView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 25/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SubmissionSuccessView: View {

    @EnvironmentObject private var navigator: Navigator

    var body: some View {
        ZStack {
            Color.black.ignoresSafeArea()
            VStack(alignment: .center) {
                LottieView(fileName: "confetti") {
                    DispatchQueue.main.asyncAfter(
                        deadline: .now() + 0.5
                    ) {
                        navigator.steps {
                            $0.dismiss()
                            $0.goBackToRoot()
                        }
                    }
                }
                .accessibility(.submissionSuccess(.lottieAnimation))
                .frame(width: 200.0, height: 200.0)
                Text(R.string.localizable.submissionSuccessTitle)
                    .font(.boldTitle)
                    .foregroundColor(.white)
                    .multilineTextAlignment(.center)
                    .padding()
                    .accessibility(.submissionSuccess(.title))
            }
        }
        .navigationBarBackButtonHidden(true)
    }
}
