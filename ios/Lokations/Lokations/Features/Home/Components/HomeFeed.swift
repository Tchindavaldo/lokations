import SwiftUI

// Reproduit : res/layout/inflate_framelayout_home_data.xml (adapteur_recycleView_framelayout_home_data.kt) :
// containerViewPager (slide 175dp + points) puis containerRv (itemligne2 x N), fond #F9F2FC.
// Bugs Android non repris : squelette "connectez-vous" de 3 s, éléments de test
// "first data N" / "N next", texte "L O A D I N G" permanent. Les données sont les cités MockData.

struct HomeFeed: View {
    private let sections = MockData.homeSections

    var body: some View {
        VStack(spacing: 0) {
            VStack(spacing: 0) {
                HomeSlidePager(slides: HomeSlide.from(MockData.allCites), autoScroll: true)
                    .frame(height: 175)
                HomeSlideDots()
                    .padding(.top, 5)
                    .padding(.horizontal, 1)
                    .frame(maxWidth: .infinity)
            }
            .padding(.horizontal, 7)
            .padding(.bottom, 30)

            LazyVStack(spacing: 0) {
                ForEach(sections) { section in
                    HomeLigneRow(cites: section.cites)
                }
            }
            .padding(.bottom, 50)
        }
        .background(DS.android(0xF9F2FC))
    }
}

/// Reproduit : res/layout/itemligne2.xml (conteneur paddingLeft/Right 7dp, ligne2 -> ligne5).
struct HomeLigneRow: View {
    let cites: [Cite]

    /// 3 cités par ligne, prises dans la section (répétées si la section en a moins).
    private var three: [Cite] {
        guard !cites.isEmpty else { return [] }
        return (0..<3).map { cites[$0 % cites.count] }
    }

    var body: some View {
        if !cites.isEmpty {
            VStack(alignment: .leading, spacing: 0) {
                HomeLigne2Section(cites: three, all: cites).modifier(HomeFadeIn(duration: 1.0))
                HomeLigne3Section(cites: three, all: cites).modifier(HomeFadeIn(duration: 0.7))
                HomeLigne4Section(cites: Array(three.reversed()), all: cites).modifier(HomeFadeIn(duration: 0.7))
                HomeLigne5Section(cite: three[0]).modifier(HomeFadeIn(duration: 0.7))
            }
            .padding(.horizontal, 7)
        }
    }
}

/// En-tête d'une ligne : titre 15sp gras + "tout voir" 13sp black_50 (ouvre la liste complète).
struct HomeLigneHeader: View {
    let title: String
    let cites: [Cite]

    var body: some View {
        HStack(spacing: 0) {
            HomeText(title, 15, DS.black, bold: true)
                .frame(maxWidth: .infinity, alignment: .leading)
            NavigationLink(value: HomeRoute.list(title: title, cites: cites)) {
                HomeText("tout voir", 13, DS.blackA(50), bold: true)
            }
            .buttonStyle(.plain)
        }
        .padding(.horizontal, 7)
        .padding(.bottom, 15)
    }
}

/// Prix au format Android "222 000".
func homePrice(_ value: Int) -> String {
    value.formatted(.number.grouping(.automatic).locale(Locale(identifier: "fr_FR")))
}
