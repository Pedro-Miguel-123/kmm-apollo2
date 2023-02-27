//
//  ProfileViewModel.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
 
class ProfileViewModel: ViewModel, ObservableObject {
    
    @Published public var desserts: [Dessert] = []
    
    let authRepository: AuthRepository
    
    init(authRepository: AuthRepository) {
        self.authRepository = authRepository
    }
    
    func fetchDesserts() {
        authRepository.getProfileDesserts(completionHandler: { [weak self] (data, error) in
            guard let self = self,
                  let desserts = data else {return}
            self.desserts = desserts
        })
    }
}
