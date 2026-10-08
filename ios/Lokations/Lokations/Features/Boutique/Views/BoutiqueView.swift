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

                    TabView(selection: $page) {
                        BoutiqueTransactionPage().tag(0)
                        BoutiqueStatistiquePage().tag(1)
                        BoutiqueShopPage().tag(2)
                        BoutiquePubPage().tag(3)
                    }
                    .tabViewStyle(.page(indexDisplayMode: .never))
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
