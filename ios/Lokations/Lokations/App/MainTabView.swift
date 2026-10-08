import SwiftUI

// Reproduit : res/layout/home.xml + res/menu/bottom_navigation_menu.xml (HomeActivity.kt).
// Barre native TabView (Liquid Glass sur iOS 26), icônes et ordre Android :
// page_1 home -> HomeFragment, page_2 search -> fragment_search, page_3 store -> fragment_boutique,
// page_4 favoris -> fragment_notif, page_5 profil -> fragment_param.
// Teinte : res/color/icon_color.xml (coché #25121F).
struct MainTabView: View {
    var body: some View {
        TabView {
            HomeView()
                .tabItem { Label { Text("home") } icon: { Image("home").renderingMode(.template) } }
            SearchView()
                .tabItem { Label { Text("search") } icon: { Image("search").renderingMode(.template) } }
            BoutiqueView()
                .tabItem { Label { Text("store") } icon: { Image("store").renderingMode(.template) } }
            NotificationsView()
                .tabItem { Label { Text("favoris") } icon: { Image("favoris").renderingMode(.template) } }
            SettingsView()
                .tabItem { Label { Text("profil") } icon: { Image("ic_baseline_settings_24").renderingMode(.template) } }
        }
        .tint(DS.android(0x25121F))
    }
}
