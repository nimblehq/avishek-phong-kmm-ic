//
//  SkeletonTextView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 07/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SkeletonUI
import SwiftUI

struct SkeletonTextView: View {

    let width: CGFloat
    var lines: Int = 1

    var body: some View {

        let linesInFloat = CGFloat(lines)
        let height = (20.0 * linesInFloat) + (6.0 * (linesInFloat - 1))

        Text(nil)
            .skeleton(
                with: true,
                size: CGSize(
                    width: width,
                    height: height
                )
            )
            .shape(type: .capsule)
            .multiline(lines: lines, scales: [1: 0.5], spacing: 6.0)
    }
}
