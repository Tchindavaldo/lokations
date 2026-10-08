import SwiftUI

// Reproduit res/layout/fragment_statistique_journanlier.xml, _hebdomadaire, _mensuel,
// _annuel (TabLayout scrollable chambre1..Chambre7 sans indicateur sur fond #F9F2FC,
// ViewPager2 de Fragment_statistique_chambre) et fragment_statistique_chambre.xml
// (RecyclerView de inflate_statistique_chambre). fragment_statistque.xml est vide.

enum CiteStatistiquePeriode: CaseIterable {
    case journalier, hebdomadaire, mensuel, annuel

    /// TabLayoutMediator : mensuel passe les titres en minuscules.
    var titles: [String] {
        let base = ["chambre1", "Chambre2", "Chambre3", "Chambre4", "Chambre5", "Chambre6", "Chambre7"]
        return self == .mensuel ? base.map { $0.lowercased() } : base
    }

    /// tabPaddingStart / tabPaddingEnd (mensuel : défaut 12dp / 0dp).
    var tabPadding: (start: CGFloat, end: CGFloat) {
        self == .mensuel ? (12, 0) : (3, 3)
    }
}

struct CiteStatistiquePeriodeView: View {
    let periode: CiteStatistiquePeriode
    @State private var selected = 0

    var body: some View {
        VStack(spacing: 0) {
            ScrollViewReader { proxy in
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 0) {
                        ForEach(periode.titles.indices, id: \.self) { i in
                            Button { selected = i } label: {
                                Text(periode.titles[i])
                                    .font(.system(size: 12))
                                    .foregroundStyle(selected == i ? DS.black : DS.blackA(50))
                                    .fixedSize()
                                    .padding(.leading, periode.tabPadding.start)
                                    .padding(.trailing, periode.tabPadding.end)
                                    .frame(minWidth: 72, maxHeight: .infinity)
                            }
                            .buttonStyle(.plain)
                            .id(i)
                        }
                    }
                }
                .onChange(of: selected) { _, new in
                    withAnimation { proxy.scrollTo(new, anchor: .center) }
                }
            }
            .frame(height: 20)
            .background(DS.white)

            TabView(selection: $selected) {
                ForEach(periode.titles.indices, id: \.self) { i in
                    CiteStatistiqueChambreList().tag(i)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .padding(.top, 5)
        }
        .background(DS.white)
    }
}

/// fragment_statistique_chambre.
struct CiteStatistiqueChambreList: View {
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 0) {
                ForEach(CiteStatistiqueChambreData.demo) { CiteStatistiqueChambreRow(data: $0) }
            }
            .padding(.top, 5)
        }
        .background(DS.white)
    }
}
