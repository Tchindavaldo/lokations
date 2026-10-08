import SwiftUI

// Reproduit : res/layout/fragment_boutique_pub.xml, blocs @id/item1_page_pub / @id/item2_page_pub
// (pastilles @drawable/round_e6e0e3_15, textes 14sp / 12sp).
struct BoutiquePubPack: View {
    let name: String
    let accent: Color
    let marketing: String
    let price: String
    let multiplier: String
    /// layout_width="20dp" du second "x40" (PACK PLUS), nil = wrap_content.
    let referencementWidth: CGFloat?
    let separator: Color
    let separatorHeight: CGFloat
    let separatorTop: CGFloat

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(name).font(.system(size: 12)).foregroundStyle(accent)

            HStack(spacing: 0) {
                Text(marketing).font(.system(size: 14)).foregroundStyle(DS.black)
                Spacer(minLength: 0)
                Text(price).font(.system(size: 14, weight: .bold)).foregroundStyle(DS.black)
                    .padding(.trailing, 15)
            }
            .padding(.top, 10)

            HStack(alignment: .top, spacing: 0) {
                target("Region Ciblé", chip: "TOUTES")
                Spacer(minLength: 0)
                target("Utilisateur Ciblé", chip: "TOUS")
            }
            .padding(.top, 10)
            .padding(.trailing, 65)

            HStack(spacing: 0) {
                gray("Visibilité sur l'application")
                bold(multiplier).padding(.leading, 5)
            }
            .padding(.top, 10)

            HStack(spacing: 0) {
                gray("Referencement dans les recherches")
                if let width = referencementWidth {
                    bold(multiplier).frame(width: width).padding(.leading, 5)
                } else {
                    bold(multiplier).padding(.leading, 5)
                }
            }
            .padding(.top, 10)

            HStack(alignment: .top, spacing: 0) {
                VStack(alignment: .leading, spacing: 0) {
                    Text("Presence sur").font(.system(size: 14)).foregroundStyle(DS.black)
                    HStack(spacing: 0) {
                        Text("La page d'acceuil").font(.system(size: 14)).foregroundStyle(DS.black)
                        bold("12/24h").padding(.leading, 5)
                    }
                }
                .padding(.horizontal, 10)
                .padding(.vertical, 7)
                .background(RoundedRectangle(cornerRadius: 15).fill(DS.android(0xE6E0E3)))
                Spacer(minLength: 0)
                action("personaliser").padding(.trailing, 10)
                action("Souscrire").padding(.trailing, 15)
            }
            .padding(.top, 10)

            Rectangle()
                .fill(separator)
                .frame(height: separatorHeight)
                .padding(.top, separatorTop)
        }
    }

    private func gray(_ text: String) -> some View {
        Text(text).font(.system(size: 14)).foregroundStyle(DS.blackA(50))
    }

    private func bold(_ text: String) -> some View {
        Text(text).font(.system(size: 14, weight: .bold)).foregroundStyle(accent)
    }

    private func target(_ title: String, chip: String) -> some View {
        HStack(alignment: .top, spacing: 0) {
            gray(title)
            Text(chip)
                .font(.system(size: 12, weight: .bold))
                .foregroundStyle(accent)
                .padding(.horizontal, 7)
                .padding(.vertical, 5)
                .background(RoundedRectangle(cornerRadius: 15).fill(DS.android(0xE6E0E3)))
                .padding(.leading, 5)
        }
    }

    private func action(_ text: String) -> some View {
        Text(text)
            .font(.system(size: 14, weight: .bold))
            .foregroundStyle(DS.black)
            .padding(.horizontal, 7)
            .padding(.vertical, 5)
            .background(RoundedRectangle(cornerRadius: 15).fill(DS.android(0xE6E0E3)))
    }
}
