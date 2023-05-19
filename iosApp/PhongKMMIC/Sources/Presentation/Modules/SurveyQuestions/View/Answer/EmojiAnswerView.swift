//
//  EmojiAnswerView.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 16/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared
import SwiftUI

struct EmojiAnswerView: View {

    private let type: EmojiType
    private let emojis: [String]
    private let hightLightStyle: EmojiHightLightStyle

    @State private var selectedIndex: Int?

    @ObservedObject var question: QuestionUiModel

    var body: some View {
        if emojis.isEmpty {
            EmptyView()
        } else {
            contentView
        }
    }

    private var contentView: some View {
        HStack(spacing: 16.0) {
            Spacer()
            ForEach(0 ..< emojis.count, id: \.self) { index in
                Button {
                    selectedIndex = index
                    guard let id = question.answers[safe: index]?.id else { return }
                    question.userInputs = [AnswerInput(id: id, content: nil)]
                } label: {
                    Text(emojis[index])
                        .font(.boldTitle)
                        .opacity(getOpacityForItem(at: index))
                }
            }
            Spacer()
        }
    }

    init(question: QuestionUiModel, count: Int = 5) {
        self.question = question
        guard let type = question.displayType.toEmojiType() else {
            emojis = []
            type = .unknown
            hightLightStyle = .leftItems
            return
        }
        self.type = type
        hightLightStyle = type.highlightStyle
        emojis = type.callAsFunction(count: count)
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
        case unknown

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
            case .unknown:
                items = []
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
