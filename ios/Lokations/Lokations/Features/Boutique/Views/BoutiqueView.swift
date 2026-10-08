import SwiftUI

// Reproduit : res/layout/fragment_boutique.xml (fragment_boutique.kt + Adapter_boutique.kt).
// En-tete #E6E0E3 (34 % de la hauteur, coins bas 35) + TabLayout puis ViewPager2 a 4 pages :
// Transaction, Statistique, Boutique, Publicite. Fondu de l'en-tete 2000 ms (onResume).
struct BoutiqueView: View {
    @State private var page = 0
    @State private var headerOpacity = 1.0

    private let titles = ["Transaction", "Statistique", "Boutique", "Publicité"]

    var body: some View {
        NavigationStack {
            GeometryReader { geo in
                VStack(spacing: 0) {
                    BoutiqueHeaderCard(
                        width: geo.size.width,
                        height: max(geo.size.height * 0.34 - 35, 0),
                        titles: titles,
                        selection: $page
                    )
                    .opacity(headerOpacity)

                    // Changement d'onglet net, sans glissement de page.
                    Group {
                        switch page {
                        case 0: BoutiqueTransactionPage()
                        case 1: BoutiqueStatistiquePage()
                        case 2: BoutiqueShopPage()
                        default: BoutiquePubPage()
                        }
                    }
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .transaction { $0.animation = nil }
                }
            }
            .background(DS.white)
            .toolbar(.hidden, for: .navigationBar)
            .modifier(BoutiqueToastModifier())
            .onAppear {
                headerOpacity = 1
                
            }
        }
    }
}
