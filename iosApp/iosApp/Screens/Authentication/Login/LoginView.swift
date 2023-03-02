//
//  LoginView.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 20/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import Foundation
import SwiftUI
@available(iOS 14.0, *)
struct LoginView: View {
    @State private var login: Bool = true
    @State private var email: String = ""
    @State private var password: String = ""
    
    @StateObject var viewModel = ViewModelFactory.viewModel(forType: LoginViewModel.self)
    
    private var label: String {
        return login ? "Login" : "Sign Up"
    }
    
    var body: some View {
        if (viewModel.token.isEmpty) {
            NavigationView {
                Form {
                    Section {
                        TextField("Email", text: $email).textCase(.lowercase)
                        SecureField("Password", text: $password)
                    }
                    Section {
                        Button(
                            action: {
                                if (login) {
                                    viewModel.signIn(email: email, password: password)
                                } else {
                                    viewModel.signUp(email: email, password: password)
                                }
                            },
                            label: { Text(label) }
                        )
                        Button(
                            action: {
                                self.login = !login
                            },
                            label: {
                                if (login) {
                                    Text("Need an account? Sign up!")
                                } else {
                                    Text("Have an account? Log in")
                                }
                            }
                        )
                    }
                }
                .navigationTitle(label)
            }
        } else {
            ProfileView(logoutHandler: {
                viewModel.deleteUserState()
            })
        }
    }
}

