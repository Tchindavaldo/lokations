import SwiftUI

/// Reproduit une ligne de `res/layout/fragment_param.xml` : marge gauche 25, boîte 30x30
/// contenant l'icône 20x20, puis bloc 55 de haut (marges 25/25) avec libellé 14 gras noir
/// et `ic_baseline_navigate_next_24` 24x24 à droite.
struct SettingsRow: View {
    let icon: String
    let tint: Color
    let title: String

    var body: some View {
        HStack(spacing: 0) {
            Image(icon)
                .renderingMode(.template)
                .resizable()
                .scaledToFill()
                .foregroundStyle(tint)
                .frame(width: 20, height: 20)
                .frame(width: 30, height: 30)
            HStack(spacing: 0) {
                Text(title)
                    .font(.system(size: 14, weight: .bold))
                    .foregroundStyle(DS.black)
                Spacer(minLength: 0)
                Image("ic_baseline_navigate_next_24")
                    .renderingMode(.template)
                    .resizable()
                    .foregroundStyle(DS.black)
                    .frame(width: 24, height: 24)
            }
            .frame(height: 55)
            .padding(.horizontal, 25)
        }
        .padding(.leading, 25)
    }
}
