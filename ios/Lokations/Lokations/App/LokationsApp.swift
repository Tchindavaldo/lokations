import SwiftUI

@main
struct LokationsApp: App {
    init() {
        // Avant toute vue : RootView choisit les services selon AppConfig.firebaseEnabled.
        AppConfig.configureFirebase()
    }

    var body: some Scene {
        WindowGroup {
            RootView()
        }
    }
}
