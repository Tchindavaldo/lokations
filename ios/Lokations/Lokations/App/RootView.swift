import SwiftUI

/// Racine : instancie les stores partagés (après configuration Firebase) et choisit le flux.
struct RootView: View {
    @StateObject private var session: SessionStore
    @StateObject private var boutique: BoutiqueStore
    @StateObject private var favorites = FavoritesStore()
    @State private var showSplash = true

    init() {
        let firebase = AppConfig.firebaseEnabled
        _session = StateObject(wrappedValue: SessionStore(
            service: firebase ? FirebaseAuthService() : DemoAuthService()))
        _boutique = StateObject(wrappedValue: BoutiqueStore(
            service: firebase ? FirestoreProductService() : DemoProductService()))
    }

    var body: some View {
        ZStack {
            if showSplash {
                SplashView()
            } else if session.isLoggedIn {
                MainTabView()
            } else {
                AuthFlowView()
            }
        }
        .animation(.easeInOut(duration: 0.4), value: showSplash)
        .animation(.easeInOut(duration: 0.4), value: session.isLoggedIn)
        .environmentObject(session)
        .environmentObject(boutique)
        .environmentObject(favorites)
        .task {
            try? await Task.sleep(for: .seconds(5))
            showSplash = false
        }
    }
}
