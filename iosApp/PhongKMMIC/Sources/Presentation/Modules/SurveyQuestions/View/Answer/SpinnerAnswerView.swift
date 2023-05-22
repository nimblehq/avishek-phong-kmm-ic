//
//  SpinnerAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SpinnerAnswerView: View {

    @State private var selectedValue = ""

    private let dummyValues = [
        "option 1",
        "option 2",
        "option 3",
        "option 4",
        "option 5",
        "option 6"
    ]

    var body: some View {
        Picker("", selection: $selectedValue) {
            ForEach(0 ..< dummyValues.count, id: \.self) { index in
                Text(dummyValues[index])
                    .font(getFontForAnswer(at: index))
                    .foregroundColor(Color.white)
                if index < dummyValues.count - 1 {
                    Divider()
                        .frame(minHeight: 0.5)
                        .background(Color.white)
                }
            }
        }
        .pickerStyle(.wheel)
        .padding(20.0)
    }

    private func getFontForAnswer(at index: Int) -> Font {
        if selectedValue == dummyValues[index] {
            return .boldLarge
        }
        return .regularLarge
    }
}
