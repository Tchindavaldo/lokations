import SwiftUI

// Reproduit : res/layout/home.xml + res/menu/bottom_navigation_menu.xml (HomeActivity.kt).
// Barre native TabView (Liquid Glass sur iOS 26), icônes et ordre Android :
// home -> HomeFragment, search -> fragment_search, store -> fragment_boutique,
// favoris -> favoris (HomeFavoritesView, au lieu de fragment_notif illogique), profil -> fragment_param.
// Teinte : res/color/icon_color.xml (coché #25121F).

enum MainTab: Hashable {
    case home, search, store, favoris, profil
}

struct MainTabView: View {
    @State private var selection: MainTab = .home

    var body: some View {
        TabView(selection: $selection) {
            HomeView(openTab: { selection = $0 })
                .modifier(MainTabBarBackground())
                .tag(MainTab.home)
                .tabItem { Label { Text("home") } icon: { Image("home").renderingMode(.template) } }
            SearchView()
                .modifier(MainTabBarBackground())
                .tag(MainTab.search)
                .tabItem { Label { Text("search") } icon: { Image("search").renderingMode(.template) } }
            BoutiqueView()
                .modifier(MainTabBarBackground())
                .tag(MainTab.store)
                .tabItem { Label { Text("store") } icon: { Image("store").renderingMode(.template) } }
            HomeFavoritesView()
                .modifier(MainTabBarBackground())
                .tag(MainTab.favoris)
                .tabItem { Label { Text("favoris") } icon: { Image("favoris").renderingMode(.template) } }
            SettingsView()
                .modifier(MainTabBarBackground())
                .tag(MainTab.profil)
                .tabItem { Label { Text("profil") } icon: { Image("ic_baseline_settings_24").renderingMode(.template) } }
        }
        .tint(DS.android(0x25121F))
    }
}

/// Fond de barre d'onglets identique sur toutes les pages : blanc opaque avant iOS 26,
/// Liquid Glass natif sur iOS 26 (aucun fond noir lors des changements de page).
private struct MainTabBarBackground: ViewModifier {
    func body(content: Content) -> some View {
        if #available(iOS 26, *) {
            content
        } else {
            content
                .toolbarBackground(DS.white, for: .tabBar)
                .toolbarBackground(.visible, for: .tabBar)
        }
    }
}
