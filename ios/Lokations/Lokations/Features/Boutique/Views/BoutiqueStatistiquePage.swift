import SwiftUI

// Reproduit : res/layout/fragment_boutique_statistique.xml (fragment_boutique_statistique.kt,
// Adapter_periode_statistique.kt) puis, par periode, fragment_statistique_journanlier /
// hebdomadaire / mensuel / annuel.xml (Adapter_statistique_chambre.kt, 7 chambres) et
// fragment_statistique_chambre.xml : pages partagees CiteStatistiquePeriodeView (CiteDetail).
struct BoutiqueStatistiquePage: View {
    @State private var period = 0

    private let periods = ["Journanlier", "Hebdomadaire", "Mensuel", "Annuel"]

    var body: some View {
        VStack(spacing: 0) {
            BoutiquePeriodTabStrip(titles: periods, selection: $period)
                .frame(height: 20)
                .padding(.top, 10)
            TabView(selection: $period) {
                ForEach(Array(CiteStatistiquePeriode.allCases.enumerated()), id: \.offset) { index, periode in
                    CiteStatistiquePeriodeView(periode: periode).tag(index)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .padding(.top, 15)
        }
        .background(DS.android(0xF9F2FC))
    }
}

/// TabLayout periode : sans indicateur, onglet choisi sur @drawable/round_black_50_30,
/// item_periode_selected (10sp gras blanc) / item_periode_unSelected (9sp noir), majuscules.
struct BoutiquePeriodTabStrip: View {
    let titles: [String]
    @Binding var selection: Int

    var body: some View {
        GeometryReader { geo in
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 0) {
                    ForEach(titles.indices, id: \.self) { index in
                        let selected = selection == index
                        Button {
                            withAnimation { selection = index }
                        } label: {
                            Text(titles[index].uppercased())
                                .font(.system(size: 12, weight: selected ? .bold : .medium))
                                .foregroundStyle(selected ? DS.white : DS.black)
                                .lineLimit(1)
                                .frame(minWidth: 72, maxHeight: .infinity)
                                .background {
                                    if selected {
                                        RoundedRectangle(cornerRadius: 30).fill(DS.blackA(50))
                                    }
                                }
                                .contentShape(Rectangle())
                        }
                        .buttonStyle(.plain)
                    }
                }
                .frame(minWidth: geo.size.width, minHeight: geo.size.height)
            }
        }
    }
}
