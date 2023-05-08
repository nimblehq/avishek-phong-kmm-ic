//
//  SkeletonSurveyContentView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 07/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SkeletonUI
import SwiftUI

struct SkeletonSurveyContentView: View {

    var body: some View {
        GeometryReader { geo in
            VStack(alignment: .leading, spacing: 16.0) {
                SkeletonTextView(width: 40.0)
                SkeletonTextView(width: 140.0, lines: 2)
                SkeletonTextView(width: min(geo.size.width - 40.0, 300.0), lines: 2)
            }
            .offset(y: geo.size.height - 180.0)
        }
    }
}
