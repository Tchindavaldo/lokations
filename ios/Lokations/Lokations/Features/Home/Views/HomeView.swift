import SwiftUI

// Reproduit : res/layout/fragment_home.xml (HomeFragment .kt).
// Bandeau black_20 de 40dp sous la barre d'état, Toolbar (marginTop 38dp),
// top_icone_nav (marginTop 5dp), puis recyclerviewHomme (marginTop 25dp).
struct HomeView: View {
    var body: some View {
        NavigationStack {
            GeometryReader { geo in
                let band = max(40, geo.safeAreaInsets.top + 2)
                ZStack(alignment: .top) {
                    DS.white
                    DS.blackA(20).frame(height: band)
                    VStack(spacing: 0) {
                        HomeToolbar()
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
        }
    }
}
