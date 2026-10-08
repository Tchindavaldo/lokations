import SwiftUI

// Onglet "favoris" (menu page_4, icône favoris) : cités ajoutées aux favoris (FavoritesStore),
// lignes de res/layout/itemligne2.xml (ligne3) sur le fond #F9F2FC de l'accueil.
struct HomeFavoritesView: View {
    @EnvironmentObject private var favorites: FavoritesStore

    private var cites: [Cite] { MockData.allCites.filter { favorites.contains($0) } }

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 25) {
                    if cites.isEmpty {
                        HomeText("Aucun favori pour le moment", 14, DS.blackA(50))
                            .frame(maxWidth: .infinity)
                            .padding(.top, 40)
                    }
                    ForEach(cites) { cite in
                        NavigationLink(value: cite) { HomeLigne3Row(cite: cite) }
                            .buttonStyle(.plain)
                    }
                }
                .padding(14)
            }
            .background(DS.android(0xF9F2FC))
            .navigationTitle("favoris")
            .navigationDestination(for: Cite.self) { CiteDetailView(cite: $0) }
        }
    }
}
