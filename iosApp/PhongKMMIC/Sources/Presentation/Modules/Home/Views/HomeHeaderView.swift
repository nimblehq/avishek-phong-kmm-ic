//
//  HomeHeaderView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 07/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct HomeHeaderView: View {

    var body: some View {
        HStack {
            VStack(spacing: 10.0) {
                Text("MONDAY, JUNE 15")
                    .foregroundColor(.white)
                    .font(.boldSmall)
                Text("TODAY")
                    .foregroundColor(.white)
                    .font(.boldLargeTitle)
            }
            Spacer()
            R.image.logoWhite.image
                .resizable()
                .frame(width: 36.0, height: 36.0)
                .cornerRadius(18.0)
        }
        .padding([.top, .horizontal], 20.0)
    }
}
