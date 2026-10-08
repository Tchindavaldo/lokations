import SwiftUI

/// Reproduit une ligne de `res/layout/fragment_notif.xml` : paddingLeft 15 / paddingRight 25,
/// CardView ronde 55x55 (image centerCrop) ou, pour la dernière, cercle #E6E0E3
/// (`round_e6e0e3_55`) avec `ic_baseline_shopping_bag_24` 24x24 ; textes 12 gras / 10 black_50 ;
/// "Consulter" 14 gras.
struct NotifRow: View {
    /// nil = dernière ligne (icône sac de courses).
    let imageName: String?

    var body: some View {
        HStack(alignment: .top, spacing: 0) {
            avatar
            VStack(alignment: .leading, spacing: 0) {
                Text("Nouvelle Cité")
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                Spacer(minLength: 0)
                Text("construction terminé")
                    .font(.system(size: 10))
                    .foregroundStyle(DS.blackA(50))
                Text("de la cité des anges à Douala au quartier bonapriso")
                    .font(.system(size: 10))
                    .foregroundStyle(DS.blackA(50))
                    .fixedSize(horizontal: false, vertical: true)
            }
            .padding(.leading, 15)
            .padding(.bottom, 2)
            .frame(maxWidth: .infinity, minHeight: 55, alignment: .topLeading)
            Text("Consulter")
                .font(.system(size: 14, weight: .bold))
                .foregroundStyle(DS.black)
        }
        .padding(.leading, 15)
        .padding(.trailing, 25)
    }

    @ViewBuilder private var avatar: some View {
        if let imageName {
            Image(imageName)
                .resizable()
                .scaledToFill()
                .frame(width: 55, height: 55)
                .background(DS.android(0x80FFFFFF, hasAlpha: true))
                .clipShape(Circle())
        } else {
            Image("ic_baseline_shopping_bag_24")
                .renderingMode(.template)
                .resizable()
                .scaledToFill()
                .foregroundStyle(DS.black)
                .frame(width: 24, height: 24)
                .frame(width: 55, height: 55)
                .background(RoundedRectangle(cornerRadius: 55).fill(DS.android(0xE6E0E3)))
        }
    }
}
