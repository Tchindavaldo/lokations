import SwiftUI

// Reproduit : res/layout/fragmentbtiqueboutique.xml (Fragment_boutique_boutique.kt) :
// filtres de statut, 4 cartes 120x80 (ajouter / modifier / supprimer), puis RecyclerView
// de inflate_chambre_infos (CiteChambreInfoRow, CiteDetail) alimente par Firestore users/user.listOfUsers via BoutiqueStore.
struct BoutiqueShopPage: View {
    @EnvironmentObject private var store: BoutiqueStore
    @State private var form: BoutiqueProductFormView.Mode?
    @State private var deleteMode = false
    @State private var filter = 0

    private let filters = ["Chambre libre", " Occupé", "impayé", "payement complet", "En Fin De contrat"]

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            statusFilters
                .padding(.leading, 15)
                .padding(.top, 10)

            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 10) {
                    BoutiqueShopActionCard(top: "Ajouter", bottom: "Un Nouvel Article") { form = .add }
                    BoutiqueShopActionCard(top: "modifier", bottom: "Un Aricle Existant") {
                        deleteMode = false
                        hint("Touchez un article pour le modifier")
                    }
                    BoutiqueShopActionCard(top: "suprimer", bottom: "Un Aricle Existant",
                                           topColor: deleteMode ? DS.black : DS.blackA(50)) {
                        deleteMode.toggle()
                        hint(deleteMode ? "Touchez un article pour le supprimer" : "Suppression annulée")
                    }
                }
                .padding(.leading, 8)
                .padding(.top, 15)
            }

            List {
                ForEach(store.products) { product in
                    CiteChambreInfoRow(item: product.item)
                        .contentShape(Rectangle())
                        .onTapGesture { tap(product) }
                        .listRowInsets(EdgeInsets())
                        .listRowSeparator(.hidden)
                        .listRowBackground(DS.white)
                        .swipeActions {
                            Button(role: .destructive) {
                                Task { _ = await store.delete(product) }
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
                ForEach(filters.indices, id: \.self) { index in
                    Text(filters[index])
                        .font(.system(size: 12, weight: filter == index ? .bold : .regular))
                        .foregroundStyle(filter == index ? DS.black : DS.blackA(50))
                        .frame(width: index == 4 ? 100 : nil, alignment: .leading)
                        .onTapGesture { filter = index }
                }
            }
        }
    }

    /// Touche d'un article : modification (par defaut) ou suppression (carte "suprimer" active).
    private func tap(_ product: Product) {
        if deleteMode {
            deleteMode = false
            Task { _ = await store.delete(product) }
        } else {
            form = .edit(product)
        }
    }

    private func hint(_ text: String) {
        store.feedback = BoutiqueStore.Feedback(message: text, isError: false)
    }
}
