//
//  BlackGradientOverlay.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 15/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct BlackGradientOverlay: View {

    var body: some View {
        Rectangle()
            .foregroundColor(.clear)
            .background(
                LinearGradient(colors: [.clear, .black], startPoint: .top, endPoint: .bottom)
            )
            .opacity(0.6)
    }
}
