import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        TabView {
            DessertListView()
                .tabItem {
                    Label("Desserts", systemImage: "list.bullet")
                }
            DessertFavoriteView()
                .tabItem {
                    Label("Favorites", systemImage: "heart.fill")
                }
            LoginView().tabItem {
                Label("Profile", systemImage: "person")
            }
        }
    }
}
