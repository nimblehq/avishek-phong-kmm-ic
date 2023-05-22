//
//  SingleFormAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct SingleFormAnswerView: View {

    @State private var content = ""

    var body: some View {
        TextEditor(placeholder: "Your answer", text: $content)
            .frame(height: 170.0)
    }
}
