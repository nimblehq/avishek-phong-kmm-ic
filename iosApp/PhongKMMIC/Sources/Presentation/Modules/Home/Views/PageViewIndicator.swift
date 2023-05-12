//
//  PageViewIndicator.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 07/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct PageViewIndicator: View {

    var numberOfPage: Int
    @Binding var currentPage: Int

    var body: some View {
        HStack {
            if numberOfPage <= 0 {
                EmptyView()
            } else {
                ForEach(0 ... numberOfPage - 1, id: \.self) { item in
                    Circle()
                        .fill(currentPage == item ? .white : .white.opacity(0.2))
                        .frame(width: 8.0, height: 8.0)
                }
            }
        }
    }
}
