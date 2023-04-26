//
//  SplashView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 11/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Combine
import Factory
import KMPNativeCoroutinesCombine
import KMPNativeCoroutinesCore
import shared
import SwiftUI

struct SplashView: View {

    @InjectedObject(\.splashViewModel) private var viewModel: shared.SplashViewModel

    @State private var nimbleLogoOpacity = 0.0

    @EnvironmentObject private var navigator: Navigator

    var body: some View {
        ZStack {
            R.image.background.image
                .resizable()
                .aspectRatio(contentMode: .fill)
                .accessibility(.splash(.background))

            R.image.logoWhite.image
                .aspectRatio(contentMode: .fill)
                .frame(maxWidth: .infinity)
                .opacity(nimbleLogoOpacity)
                .accessibility(.splash(.nimbleLogo))
                .onAppear {
                    withAnimation(.easeIn(duration: 1.0).delay(0.5)) {
                        nimbleLogoOpacity = 1.0
                    }
                }
                .onAnimationCompleted(for: nimbleLogoOpacity) {
                    if !viewModel.checkIfUserLoggedIn() {
                        navigator.showScreen(screen: .login, with: .root)
                    }
                }
        }
        .ignoresSafeArea()
    }
}

struct SplashView_Previews: PreviewProvider {

    static var previews: some View {
        SplashView()
    }
}
