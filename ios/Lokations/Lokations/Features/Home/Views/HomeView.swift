import SwiftUI

// Reproduit : res/layout/fragment_home.xml (HomeFragment .kt).
// Bandeau black_20 de 40dp sous la barre d'état, Toolbar (marginTop 38dp),
// top_icone_nav (marginTop 5dp), puis recyclerviewHomme (marginTop 25dp).

/// Destinations internes à l'accueil (listes "tout voir", catégories, recherche).
enum HomeRoute: Hashable {
    case list(title: String, cites: [Cite])
}

struct HomeView: View {
    var openTab: (MainTab) -> Void = { _ in }
    @State private var path = NavigationPath()
    @State private var showNotifications = false

    var body: some View {
        NavigationStack(path: $path) {
            GeometryReader { geo in
                let band = max(40, geo.safeAreaInsets.top + 2)
                ZStack(alignment: .top) {
                    DS.white
                    DS.blackA(20).frame(height: band)
                    VStack(spacing: 0) {
                        HomeToolbar(
                            onSearch: search,
                            onCart: { openTab(.store) },
                            onNotifications: { showNotifications = true },
                            onAccount: { openTab(.profil) })
                        HomeCategoryNav().padding(.top, 5)
                        ScrollView { HomeFeed() }
                            .padding(.top, 25)
                    }
                    .padding(.top, band - 2)
                }
                .ignoresSafeArea(edges: .top)
            }
            .toolbar(.hidden, for: .navigationBar)
            .navigationDestination(for: Cite.self) { CiteDetailView(cite: $0) }
            .navigationDestination(for: HomeRoute.self) { route in
                switch route {
                case let .list(title, cites): HomeCiteListView(title: title, cites: cites)
                }
            }
            .sheet(isPresented: $showNotifications) { NotificationsView() }
        }
    }

    private func search(_ query: String) {
        let q = query.trimmingCharacters(in: .whitespaces).lowercased()
        guard !q.isEmpty else { return }
        let found = MockData.allCites.filter {
            $0.name.lowercased().contains(q) || $0.city.lowercased().contains(q)
                || $0.district.lowercased().contains(q)
        }
        path.append(HomeRoute.list(title: query, cites: found))
    }
}
