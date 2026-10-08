import SwiftUI

// Reproduit : res/layout/fragment_boutique_pub.xml (fragment_boutique_pub.kt, sans logique) :
// PACK EXTRA (#CC039AFF) puis PACK PLUS (#CCFF0303), marges 15dp / 30dp.
struct BoutiquePubPage: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            BoutiquePubPack(
                name: "PACK EXTRA",
                accent: DS.android(0xCC039AFF, hasAlpha: true),
                marketing: "Marketing Agressif",
                price: "10 0000/jours",
                multiplier: "x100",
                referencementWidth: nil,
                separator: DS.black,
                separatorHeight: 0.7,
                separatorTop: 20
            )
            BoutiquePubPack(
                name: "PACK PLUS",
                accent: DS.android(0xCCFF0303, hasAlpha: true),
                marketing: "Marketing Modéré",
                price: "50000/jours",
                multiplier: "x40",
                referencementWidth: 20,
                separator: DS.white,
                separatorHeight: 1,
                separatorTop: 25
            )
            .padding(.top, 45)
        }
        .padding(.leading, 15)
        .padding(.top, 30)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .topLeading)
        .clipped()
        .background(DS.white)
    }
}
