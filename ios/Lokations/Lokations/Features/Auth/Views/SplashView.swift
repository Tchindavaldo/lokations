import SwiftUI

struct SplashView: View {
    var body: some View {
        ZStack {
            Image("m88").resizable().scaledToFill().ignoresSafeArea()
            DS.scrim(0.55).ignoresSafeArea()
            VStack(spacing: DS.Space.sm) {
                Image(systemName: "house.fill")
                    .font(.system(size: 64))
                Text("Lokations")
                    .font(.system(size: 40, weight: .bold))
                Text("Trouvez votre chambre idéale")
                    .foregroundStyle(DS.onDarkAlpha(0.8))
            }
            .foregroundStyle(DS.onDark)
        }
        .transition(.opacity)
    }
}
