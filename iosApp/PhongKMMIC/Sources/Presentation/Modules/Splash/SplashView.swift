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
        GeometryReader { geometry in
            ZStack {
                R.image.background.image
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(width: geometry.size.width, height: geometry.size.height)

                R.image.logoWhite.image
                    .aspectRatio(contentMode: .fill)
                    .frame(maxWidth: .infinity)
                    .opacity(isLoaded ? 1 : 0)
            }
        }
        .ignoresSafeArea()
        .animation(.easeIn(duration: 1.0).delay(0.5), value: isLoaded)
        .onAppear {
            isLoaded.toggle()
        }
    }
}
