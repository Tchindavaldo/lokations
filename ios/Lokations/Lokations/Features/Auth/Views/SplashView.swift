// Reproduit activity_splash.xml : fond #FFB1E0, ImageView @drawable/notif
// (wrap_content) centrée. Durée d'affichage pilotée par RootView.
import SwiftUI

struct SplashView: View {
    var body: some View {
        ZStack {
            DS.android(0xFFB1E0).ignoresSafeArea()
            Image("notif")
        }
        .transition(.opacity)
    }
}
