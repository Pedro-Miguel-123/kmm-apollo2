//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class LoginViewModel: ViewModel, ObservableObject {
    
    let authRepository: AuthRepository
    
    @Published public var token: String = ""
    
    init(authRepository: AuthRepository) {
        self.authRepository = authRepository
        token = authRepository.getUserState()?.token ?? ""
    }
    
    func signIn(email: String, password: String) {
        authRepository.signIn(userInput: UserInput(email: email, password: password)) { [weak self] (data, error) in
            guard let self = self,
                  let token = data else { return }
            self.token = token
        }
    }
    
    func signUp(email: String, password: String) {
        authRepository.signUp(userInput: UserInput(email: email, password: password)) { [weak self] (data, error) in
            guard let self = self,
                  let token = data else { return }
            self.token = token
        }
        print(self.token)
    }
    
    func getUserState() -> UserState? {
        return authRepository.getUserState()
    }
    
    func deleteUserState() {
        authRepository.deleteUserState()
        self.token = ""
    }
}
