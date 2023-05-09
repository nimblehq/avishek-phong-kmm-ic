//
//  Image+Kingfisher.swift
//  PhongKMMIC
//
//  Created by Phong Vo on 04/05/2023.
//  Copyright © 2023 Nimble. All rights reserved.
//

import Kingfisher
import SwiftUI

extension Image {

    static func url(_ urlString: String) -> KFImage {
        let url = URL(string: urlString) ?? URL(fileURLWithPath: "")
        return KFImage(url)
    }
}
