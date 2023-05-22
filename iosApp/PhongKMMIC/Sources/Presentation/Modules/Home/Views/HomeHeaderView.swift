//
//  HomeHeaderView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 07/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct HomeHeaderView: View {

    @Binding var userAvatarUrl: String
    @Binding var today: String

    var body: some View {
        HStack {
            VStack(spacing: 10.0) {
                Text(today)
                    .foregroundColor(.white)
                    .font(.boldSmall)
                    .frame(alignment: .leading)
                Text(R.string.localizable.homeHeaderToday)
                    .foregroundColor(.white)
                    .font(.boldLargeTitle)
                    .frame(alignment: .leading)
            }
            Spacer()
            Image.url(userAvatarUrl)
                .resizable()
                .scaledToFill()
                .clipped()
                .frame(width: 36.0, height: 36.0)
                .cornerRadius(18.0)
                .accessibility(.home(.userAvatar))
        }
        .padding([.top, .horizontal], 20.0)
    }
}
