import SwiftUI

/// Boutique du bailleur (équivalent fragment_boutique + sous-onglets).
struct BoutiqueView: View {
    enum Tab: String, CaseIterable, Identifiable {
        case products = "Produits", history = "Historique", stats = "Statistiques"
        var id: String { rawValue }
    }

    @State private var tab: Tab = .products

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                Picker("Section", selection: $tab) {
                    ForEach(Tab.allCases) { Text($0.rawValue).tag($0) }
                }
                .pickerStyle(.segmented)
                .padding(DS.Space.md)

                switch tab {
                case .products: BoutiqueProductsList()
                case .history: BoutiqueHistoryList()
                case .stats: BoutiqueStatsView()
                }
            }
            .navigationTitle("Boutique")
        }
    }
}
