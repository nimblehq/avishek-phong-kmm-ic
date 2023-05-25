//
//  QuestionDisplayType+EmojiType.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 20/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import shared

extension QuestionDisplayType {

    func toEmojiType() -> EmojiAnswerView.EmojiType? {
        switch self {
        case .smiley:
            return .smile
        case .star:
            return .star
        case .heart:
            return .heart
        default:
            return nil
        }
    }
}
