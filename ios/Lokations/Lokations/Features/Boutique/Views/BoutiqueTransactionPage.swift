import SwiftUI

// Reproduit : res/layout/fragment_boutique_historique_transcastion.xml
// (fragment_boutique_historique_transcastion.kt : fondu 2000 ms apres 300 ms puis 650 ms).
struct BoutiqueTransactionPage: View {
    @State private var firstOpacity = 0.0
    @State private var secondOpacity = 0.0

    private static let green = DS.android(0xCC03FF25, hasAlpha: true)
    private static let red = DS.android(0xCCFF0303, hasAlpha: true)

    private let compte: [BoutiqueTransactionLine] = [
        .init(label: "09 - 10 - 2024 ", value: "", topPadding: 15),
        .init(label: "Operation Effectuer", value: "Retrait d'argent", valueBold: false),
        .init(label: "Montant De L'opeartion", value: "- 731 000", valueColor: Self.red),
        .init(label: "Nouveau Solde", labelColor: DS.black, value: "9 609 000f", valueSize: 16)
    ]

    private let chambre: [BoutiqueTransactionLine] = [
        .init(label: "09 - 10 - 2024 ", value: "", topPadding: 15),
        .init(label: "Operation Effectuer", value: "Payement Louer", valueBold: false),
        .init(label: "Versement N°", value: "2"),
        .init(label: "Versement Restant", value: "0"),
        .init(label: "Montant De L'opeartion", value: "+ 31 000", valueColor: Self.green),
        .init(label: "Nouveau Solde", labelColor: DS.black, value: "9 609 000f", valueSize: 16)
    ]

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            BoutiqueTransactionBlock(title: "Virement Du Compte", lines: compte)
                .padding(.top, 30)
                .opacity(firstOpacity)

            VStack(alignment: .leading, spacing: 0) {
                Rectangle().fill(DS.black).frame(height: 1).padding(.leading, 15)
                BoutiqueTransactionBlock(title: "Virement Chambre 1", lines: chambre)
                    .padding(.top, 20)
            }
            .padding(.top, 65)
            .opacity(secondOpacity)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .clipped()
        .background(DS.white)
        .onAppear(perform: animate)
        .onDisappear {
            firstOpacity = 0
            secondOpacity = 0
        }
    }

    private func animate() {
        withAnimation(.easeInOut(duration: 2).delay(0.3)) { firstOpacity = 1 }
        withAnimation(.easeOut(duration: 2).delay(0.65)) { secondOpacity = 1 }
    }
}
