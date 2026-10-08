import SwiftUI

/// Reproduit `res/layout/fragment_search.xml` (fragment_search.kt) :
/// FrameLayout paddingTop 20 > fond #FFFFFF > Toolbar (barre de recherche + icônes),
/// TabLayout 320 (onglets de tri), RecyclerView fond #F9F2FC (blocs `icon3.xml`).
/// Comportement corrigé (R13) : le champ filtre, les onglets trient, un bloc par ville.
struct SearchView: View {
    @State private var query = ""
    @State private var selectedTab = 0

    private var results: [Cite] {
        let q = query.trimmingCharacters(in: .whitespaces)
        let all = MockData.allCites
        let filtered = q.isEmpty ? all : all.filter {
            $0.name.localizedCaseInsensitiveContains(q)
                || $0.city.localizedCaseInsensitiveContains(q)
                || $0.district.localizedCaseInsensitiveContains(q)
        }
        return SearchSort.allCases[selectedTab].sort(filtered)
    }

    /// Villes dans l'ordre d'apparition après tri.
    private var cities: [String] {
        var seen: [String] = []
        for cite in results where !seen.contains(cite.city) { seen.append(cite.city) }
        return seen
    }

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                SearchToolbar(query: $query)
                SearchTabStrip(titles: SearchSort.allCases.map(\.rawValue), selection: $selectedTab)
                    .frame(width: 320, height: 24)
                    .padding(.top, 5)
                ScrollView {
                    LazyVStack(spacing: 0) {
                        if results.isEmpty {
                            Text("Aucune cité trouvée")
                                .font(.system(size: 13))
                                .foregroundStyle(DS.blackA(50))
                                .padding(.top, 40)
                        }
                        ForEach(cities, id: \.self) { city in
                            let inCity = results.filter { $0.city == city }
                            SearchSectionBlock(title: city, topCites: inCity,
                                               bottomTitle: "autres cités",
                                               bottomCites: results.filter { $0.city != city })
                        }
                    }
                }
                .scrollDismissesKeyboard(.immediately)
                .background(DS.android(0xF9F2FC))
                .padding(.top, 10)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .background(DS.white)
            .padding(.top, 20)
            .toolbar(.hidden, for: .navigationBar)
            .navigationDestination(for: Cite.self) { CiteDetailView(cite: $0) }
        }
    }
}

/// Onglets de fragment_search.kt sans les doublons, chacun associé à un tri.
enum SearchSort: String, CaseIterable {
    case prix, klity, comment, ville, position, like, visite

    func sort(_ cites: [Cite]) -> [Cite] {
        switch self {
        case .prix: cites.sorted { $0.pricePerMonth < $1.pricePerMonth }
        case .klity: cites.sorted { $0.freeRooms > $1.freeRooms }
        case .comment: cites.sorted { $0.name < $1.name }
        case .ville: cites.sorted { $0.city < $1.city }
        case .position: cites.sorted { $0.district < $1.district }
        case .like: cites.sorted { $0.pricePerMonth > $1.pricePerMonth }
        case .visite: cites.sorted { $0.releasingRooms > $1.releasingRooms }
        }
    }
}
