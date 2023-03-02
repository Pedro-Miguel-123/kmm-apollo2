//
//  DessertForm.swift
//  iosApp
//
//  Created by Pedro Miguel Santos on 22/02/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import Foundation
import shared

struct DessertForm: View {
    
    var handler: (Dessert, ActionType) -> Void
    
    let dessertId: String
    @State var name: String
    @State var description: String
    @State var imageUrl: String
    
    private var isEditing: Bool {
        return dessertId != "new"
    }
    
    private var label: String {
        return isEditing ? "Edit" : "Create"
    }
    
    var body: some View {
        Form {
            Section(header: Text("\(label) Dessert")) {
                TextField("Name", text: $name)
                TextField("Description", text: $description)
                TextField("Image URL", text: $imageUrl)
            }
            Section {
                Button (
                    action: {
                        let action: ActionType = isEditing ? .update : .create
                        self.handler(Dessert(id: dessertId, userId: "", name: name, description: description, imageUrl: imageUrl), action)
                    },
                    label: { Text(label) }
                )
                if isEditing {
                    Button (
                        action: {
                            self.handler(Dessert(id: dessertId, userId: "", name: "", description: "", imageUrl: ""), ActionType.delete_)
                        },
                        label: { Text("Delete")}
                    )
                }
            }
        }
    }
}
