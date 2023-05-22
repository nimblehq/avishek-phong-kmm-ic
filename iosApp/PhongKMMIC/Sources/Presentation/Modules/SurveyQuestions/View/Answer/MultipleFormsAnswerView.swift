//
//  MultipleFormsAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 17/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct MultipleFormsAnswerView: View {

    private let dummyPlaceholder = [
        "Your answer 1",
        "Your answer 2",
        "Your answer 3",
        "Your answer 4",
        "Your answer 5"
    ]

    // TODO: relace this with actual logic for binding user's inputs
    @State private var values = [
        "",
        "",
        "",
        "",
        ""
    ]

    var body: some View {
        VStack(spacing: 16.0) {
            ForEach(0 ..< dummyPlaceholder.count, id: \.self) { index in
                TextField(dummyPlaceholder[index], text: $values[index])
                    .primaryTextField()
                    .padding(.horizontal, 12.0)
                    // TODO: replace with actual logic for binding user's inputs
                    .onChange(of: values) { _ in
                        print("User input: \(values[index])")
                    }
            }
        }
    }
}
