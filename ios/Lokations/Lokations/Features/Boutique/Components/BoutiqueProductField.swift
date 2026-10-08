import SwiftUI

// Reproduit : res/layout/activity_ajout_produit.xml, LinearLayout 45dp @drawable/round_black_25_15 :
// icone 14x16 (poids 0.2) + EditText 15sp gras white_50 (poids 1) [+ icone visibilite (poids 0.2)].
struct BoutiqueProductField: View {
    let icon: String
    let placeholder: String
    @Binding var text: String
    var trailingIcon: String?

    var body: some View {
        GeometryReader { geo in
            let unit = geo.size.width / (trailingIcon == nil ? 1.2 : 1.4)
            HStack(spacing: 0) {
                iconBox(icon).frame(width: unit * 0.2)
                TextField("", text: $text, prompt: Text(placeholder).foregroundStyle(DS.whiteA(70)))
                    .font(.system(size: 15, weight: .bold))
                    .foregroundStyle(DS.white)
                    .tint(DS.white)
                    .padding(.horizontal, 4)
                    .frame(width: unit, alignment: .leading)
                if let trailingIcon {
                    iconBox(trailingIcon).frame(width: unit * 0.2)
                }
            }
            .frame(height: geo.size.height)
        }
        .frame(height: 45)
        .background(RoundedRectangle(cornerRadius: 15).fill(DS.blackA(25)))
    }

    private func iconBox(_ name: String) -> some View {
        Image(name)
            .resizable()
            .frame(width: 14, height: 16)
            .frame(maxHeight: .infinity)
    }
}
