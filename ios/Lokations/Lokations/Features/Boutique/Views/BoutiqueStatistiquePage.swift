import SwiftUI

// Reproduit : res/layout/fragment_boutique_statistique.xml (fragment_boutique_statistique.kt,
// Adapter_periode_statistique.kt) puis, par periode, fragment_statistique_journanlier /
// hebdomadaire / mensuel / annuel.xml (Adapter_statistique_chambre.kt, 7 chambres) et
// fragment_statistique_chambre.xml (Fragment_statistique_chambre.kt).
struct BoutiqueStatistiquePage: View {
    @State private var period = 0

    private let periods = ["Journanlier", "Hebdomadaire", "Mensuel", "Annuel"]

    var body: some View {
        VStack(spacing: 0) {
            BoutiquePeriodTabStrip(titles: periods, selection: $period)
                .frame(height: 20)
                .padding(.top, 10)
            TabView(selection: $period) {
                ForEach(periods.indices, id: \.self) { index in
                    BoutiqueStatPeriodPage(lowercased: index == 2, mensuel: index == 2)
                        .tag(index)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .padding(.top, 15)
        }
        .background(DS.android(0xF9F2FC))
    }
}

/// fragment_statistique_<periode>.xml : TabLayout 20dp (chambre1 … Chambre7) + ViewPager2.
struct BoutiqueStatPeriodPage: View {
    let lowercased: Bool
    let mensuel: Bool
    @State private var chambre = 0

    private var titles: [String] {
        let raw = ["chambre1", "Chambre2", "Chambre3", "Chambre4", "Chambre5", "Chambre6", "Chambre7"]
        return lowercased ? raw.map { $0.lowercased() } : raw
    }

    var body: some View {
        VStack(spacing: 0) {
            BoutiqueChambreTabStrip(
                titles: titles,
                selection: $chambre,
                paddingStart: mensuel ? 12 : 3,
                paddingEnd: mensuel ? 0 : 3,
                contentStart: mensuel ? -50 : 0
            )
            .frame(height: 20)
            TabView(selection: $chambre) {
                ForEach(titles.indices, id: \.self) { index in
                    BoutiqueStatChambrePage().tag(index)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .padding(.top, 5)
        }
        .background(DS.android(0xF9F2FC))
    }
}

/// fragment_statistique_chambre.xml : RecyclerView (marginTop 5dp) de inflate_statistique_chambre.
struct BoutiqueStatChambrePage: View {
    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                ForEach(BoutiqueStatData.samples) { data in
                    BoutiqueStatChambreRow(data: data)
                        .padding(.bottom, 20)
                }
            }
            .padding(.top, 5)
        }
        .background(DS.white)
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
                                .font(.system(size: selected ? 10 : 9, weight: selected ? .bold : .medium))
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

/// TabLayout chambre : sans indicateur, item_chambre_(un)selected 10sp, black_50 / black.
struct BoutiqueChambreTabStrip: View {
    let titles: [String]
    @Binding var selection: Int
    let paddingStart: CGFloat
    let paddingEnd: CGFloat
    let contentStart: CGFloat

    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 0) {
                ForEach(titles.indices, id: \.self) { index in
                    Button {
                        withAnimation { selection = index }
                    } label: {
                        Text(titles[index])
                            .font(.system(size: 10, weight: .medium))
                            .foregroundStyle(selection == index ? DS.black : DS.blackA(50))
                            .lineLimit(1)
                            .padding(.leading, paddingStart)
                            .padding(.trailing, paddingEnd)
                            .frame(minWidth: 72, maxHeight: .infinity)
                            .contentShape(Rectangle())
                    }
                    .buttonStyle(.plain)
                }
            }
            .offset(x: contentStart)
        }
    }
}
