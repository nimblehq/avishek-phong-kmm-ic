//
//  SplashView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 11/04/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SplashView: View {

    @State private var isLoaded = false

    var body: some View {
        ZStack {
            R.image.background.image
                .resizable()
                .aspectRatio(contentMode: .fill)

            R.image.logoWhite.image
                .aspectRatio(contentMode: .fill)
                .frame(maxWidth: .infinity)
                .opacity(
                    isLoaded ? 1.0 : 0.0
                )
                .animation(.easeIn(duration: 1.0).delay(0.5), value: isLoaded)
                .onAppear {
                    withAnimation {
                        isLoaded.toggle()
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
