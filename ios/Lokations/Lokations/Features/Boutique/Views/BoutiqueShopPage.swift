import SwiftUI

// Reproduit : res/layout/fragmentbtiqueboutique.xml (Fragment_boutique_boutique.kt) :
// filtres de statut, 4 cartes 120x80 (ajouter / modifier / supprimer), puis RecyclerView
// de inflate_chambre_infos alimente par Firestore users/user.listOfUsers via BoutiqueStore.
struct BoutiqueShopPage: View {
    @EnvironmentObject private var store: BoutiqueStore
    @State private var form: BoutiqueProductFormView.Mode?

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            statusFilters
                .padding(.leading, 15)
                .padding(.top, 10)

            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 10) {
                    BoutiqueShopActionCard(top: "Ajouter", bottom: "Un Nouvel Article") { form = .add }
                    BoutiqueShopActionCard(top: "modifier", bottom: "Un Aricle Existant") { form = .update }
                    BoutiqueShopActionCard(top: "suprimer", bottom: "Un Aricle Existant")
                    BoutiqueShopActionCard(top: "suprimer", bottom: "Un Aricle Existant",
                                           topColor: DS.whiteA(50), bottomColor: DS.white)
                }
                .padding(.leading, 8)
                .padding(.top, 15)
            }

            List {
                ForEach(store.products) { product in
                    BoutiqueChambreInfoRow(title: product.item)
                        .listRowInsets(EdgeInsets())
                        .listRowSeparator(.hidden)
                        .listRowBackground(DS.white)
                        .swipeActions {
                            Button(role: .destructive) {
                                Task { await store.delete(product) }
                            } label: {
                                Text("supprimer")
                            }
                        }
                }
            }
            .listStyle(.plain)
            .scrollContentBackground(.hidden)
            .environment(\.defaultMinListRowHeight, 0)
            .refreshable { await store.load() }
            .padding(.horizontal, 8)
            .padding(.top, 10)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .background(DS.white)
        .task { await store.load() }
        .sheet(item: $form) { mode in
            BoutiqueProductFormView(mode: mode)
        }
    }

    private var statusFilters: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 10) {
                Text("Chambre libre")
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                filter(" Occupé")
                filter("impayé")
                filter("payement complet")
                filter("En Fin De contrat").frame(width: 100, alignment: .leading)
            }
        }
    }

    private func filter(_ text: String) -> some View {
        Text(text).font(.system(size: 12)).foregroundStyle(DS.blackA(50))
    }
}
