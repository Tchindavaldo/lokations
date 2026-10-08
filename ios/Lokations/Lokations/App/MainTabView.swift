import SwiftUI

/// Barre d'onglets principale (équivalent HomeActivity + BottomNavigationView).
struct MainTabView: View {
    var body: some View {
        TabView {
            HomeView()
                .tabItem { Label("Accueil", systemImage: "house.fill") }
            SearchView()
                .tabItem { Label("Recherche", systemImage: "magnifyingglass") }
            BoutiqueView()
                .tabItem { Label("Boutique", systemImage: "storefront") }
            NotificationsView()
                .tabItem { Label("Notifications", systemImage: "bell") }
            SettingsView()
                .tabItem { Label("Paramètres", systemImage: "gearshape") }
        }
        .tint(DS.ink)
    }
}
