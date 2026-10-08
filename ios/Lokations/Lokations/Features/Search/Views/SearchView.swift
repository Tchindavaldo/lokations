import SwiftUI

/// Reproduit `res/layout/fragment_search.xml` (fragment_search.kt) :
/// FrameLayout paddingTop 20 > fond #FFFFFF > Toolbar (barre de recherche + icônes),
/// TabLayout 320x20 (10 onglets), RecyclerView fond #F9F2FC (5 blocs `icon3.xml`).
struct SearchView: View {
    @State private var query = ""
    @State private var selectedTab = 0

    /// Onglets ajoutés dans fragment_search.kt, dans le même ordre.
    private let tabs = ["prix", "klity", "comment", "ville", "position",
                        "like", "visite", "prix", "klity", "comment"]

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                SearchToolbar(query: $query)
                SearchTabStrip(titles: tabs, selection: $selectedTab)
                    .frame(width: 320, height: 20)
                    .padding(.top, 5)
                ScrollView {
                    LazyVStack(spacing: 0) {
                        ForEach(0..<5, id: \.self) { _ in
                            SearchSectionBlock(topCites: SearchData.hypocrate,
                                               bottomCites: SearchData.rose)
                        }
                    }
                }
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

/// Données en dur de fragment_search.kt (data2Search / data3Search), toutes en `m88`.
enum SearchData {
    static let hypocrate: [Cite] = MockData.hypocrate.map { cite in
        var copy = cite
        copy.imageName = "m88"
        return copy
    }

    static let rose: [Cite] = (0..<4).map { _ in
        Cite(imageName: "m88", gallery: ["m88"], name: "cité rose", pricePerMonth: 20000,
             city: "Douala", district: "Makepe", summary: MockData.rose[0].summary)
    }
}
