//
//  EmojiAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import SwiftUI

struct EmojiAnswerView: View {

    private let type: EmojiType
    private let emojis: [String]
    private let hightLightStyle: EmojiHightLightStyle

    @State private var selectedIndex: Int?

    var body: some View {
        HStack(spacing: 16.0) {
            Spacer()
            ForEach(0 ..< emojis.count, id: \.self) { index in
                Button {
                    selectedIndex = index
                    print("Did select emoji at: \(index)")
                } label: {
                    Text(emojis[index])
                        .font(.boldTitle)
                        .opacity(getOpacityForItem(at: index))
                }
            }
            Spacer()
        }
    }

    init(type: EmojiType, count: Int) {
        self.type = type
        emojis = type(count: count)
        hightLightStyle = type.highlightStyle
    }

    private func getOpacityForItem(at index: Int) -> CGFloat {
        var isHightLighted = false
        switch hightLightStyle {
        case .leftItems:
            isHightLighted = index <= (selectedIndex ?? -1)
        case .single:
            isHightLighted = index == selectedIndex
        }

        if isHightLighted {
            return 1.0
        }
        return 0.5
    }
}

// MARK: - Enums

extension EmojiAnswerView {

    enum EmojiHightLightStyle {

        case leftItems
        case single
    }

    enum EmojiType: String {

        case like
        case smile
        case heart
        case star

        func callAsFunction(count: Int) -> [String] {
            let items: [String]
            switch self {
            case .like:
                items = Array(repeatElement("👍🏻", count: count))
            case .smile:
                items = Array(["😡", "😕", "😐", "🙂", "😄"].prefix(count))
            case .heart:
                items = Array(repeatElement("❤️", count: count))
            case .star:
                items = Array(repeatElement("⭐️", count: count))
            }
            return items
        }

        var highlightStyle: EmojiHightLightStyle {
            switch self {
            case .smile:
                return .single
            default:
                return .leftItems
            }
        }
    }
}

struct EmojiAnswerView_Previews: PreviewProvider {

    static var previews: some View {
        EmojiAnswerView(type: .smile, count: 5)
    }
}
