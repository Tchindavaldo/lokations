import SwiftUI

/// Recherche (équivalent fragment_search) : texte libre + critère de tri.
struct SearchView: View {
    @State private var query = ""
    @State private var criterion: SearchCriterion = .price

    private var results: [Cite] {
        let all = MockData.allCites
        let filtered = query.isEmpty ? all : all.filter {
            $0.name.localizedCaseInsensitiveContains(query)
                || $0.city.localizedCaseInsensitiveContains(query)
                || $0.district.localizedCaseInsensitiveContains(query)
        }
        return criterion.sort(filtered)
    }

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: DS.Space.md) {
                    SearchCriterionBar(selection: $criterion)

                    if results.isEmpty {
                        ContentUnavailableCompat(query: query)
                    } else {
                        LazyVStack(spacing: DS.Space.md) {
                            ForEach(results) { cite in
                                NavigationLink(value: cite) { SearchResultRow(cite: cite) }
                                    .buttonStyle(.plain)
                            }
                        }
                        .padding(.horizontal, DS.Space.md)
                    }
                }
                .padding(.vertical, DS.Space.md)
            }
            .searchable(text: $query, prompt: "Cité, ville, quartier")
            .navigationTitle("Recherche")
            .navigationDestination(for: Cite.self) { CiteDetailView(cite: $0) }
        }
    }
}

enum SearchCriterion: String, CaseIterable, Identifiable {
    case price = "prix"
    case city = "ville"
    case availability = "disponibilité"
    case name = "nom"

    var id: String { rawValue }

    func sort(_ cites: [Cite]) -> [Cite] {
        switch self {
        case .price: cites.sorted { $0.pricePerMonth < $1.pricePerMonth }
        case .city: cites.sorted { $0.city < $1.city }
        case .availability: cites.sorted { $0.freeRooms > $1.freeRooms }
        case .name: cites.sorted { $0.name < $1.name }
        }
    }
}
