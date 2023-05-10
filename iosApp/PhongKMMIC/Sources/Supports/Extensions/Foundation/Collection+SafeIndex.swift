//
//  Collection+SafeIndex.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 08/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Foundation

extension Collection {

    subscript (safe index: Index) -> Element? {
        indices.contains(index) ? self[index] : nil
    }
}
