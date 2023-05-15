//
//  SkeletonHomeView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 07/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SkeletonHomeView: View {

    var body: some View {
        Color.black
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .ignoresSafeArea()
        VStack {
            SkeletonHomeHeaderView()
            SkeletonSurveyContentView()
        }
        .padding(.horizontal, 20.0)
    }
}
