import SwiftUI

// Reproduit : res/layout/inflate_framelayout_home_data.xml (adapteur_recycleView_framelayout_home_data.kt) :
// containerViewPager (slide 175dp + points), containerRv (itemligne2 x N + "L O A D I N G . . ."),
// squelette "C O N N E C T E R   V O U S   A   I N T E R N E T" ; fond #F9F2FC.
// Chargement : 3 s puis 5 éléments "first data N ", puis 5 "N next" en fin de liste.

/// ItemsLigne2Model (champs utilisés par CustomHomeAdapterItemLigne2).
struct HomeLigne2Item: Identifiable {
    let id = UUID()
    let image: String
    let categorie: String
    let itemCategorie: String
    let prix: String
    let text: String
    let lieux: String
}

struct HomeFeed: View {
    @State private var items: [HomeLigne2Item] = []
    @State private var loaded = false
    @State private var iter = 1
    @State private var isLoadingMore = false

    var body: some View {
        VStack(spacing: 0) {
            VStack(spacing: 0) {
                VStack(spacing: 0) {
                    HomeSlidePager(slides: HomeSlide.all, autoScroll: loaded)
                        .frame(height: 175)
                    HomeSlideDots()
                        .padding(.top, 5)
                        .padding(.horizontal, 1)
                        .frame(maxWidth: .infinity)
                }
                .padding(.horizontal, 7)
                .padding(.bottom, 30)

                LazyVStack(spacing: 0) {
                    ForEach(Array(items.enumerated()), id: \.element.id) { index, item in
                        HomeLigneRow(item: item, index: index)
                            .onAppear {
                                if item.id == items.last?.id { loadMore() }
                            }
                    }
                }
                .padding(.bottom, 50)
                .overlay(alignment: .bottom) {
                    Text("L O A D I N G . . .")
                        .font(.system(size: 14))
                        .foregroundStyle(DS.black)
                        .frame(maxWidth: .infinity)
                        .frame(height: 60)
                }
            }
            .opacity(loaded ? 1 : 0)

            Text("C O N N E C T E R   V O U S   A   I N T E R N E T")
                .font(.system(size: 14))
                .foregroundStyle(DS.black)
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 7)
                .opacity(loaded ? 0 : 1)
        }
        .background(DS.android(0xF9F2FC))
        .task {
            guard !loaded else { return }
            try? await Task.sleep(for: .seconds(3))
            items = makeItems { "first data \($0) " }
            withAnimation(.easeInOut(duration: 1.5)) { loaded = true }
        }
    }

    private func makeItems(_ name: (Int) -> String) -> [HomeLigne2Item] {
        var result: [HomeLigne2Item] = []
        for _ in 0..<5 {
            result.append(HomeLigne2Item(image: "m88", categorie: name(iter), itemCategorie: "chambre 3",
                                         prix: "300 000/ans", text: "En cour de l'iberartion", lieux: "Douala"))
            iter += 1
        }
        return result
    }

    private func loadMore() {
        guard loaded, !isLoadingMore else { return }
        isLoadingMore = true
        Task {
            try? await Task.sleep(for: .milliseconds(450))
            items += makeItems { "\($0) next" }
            isLoadingMore = false
        }
    }
}

/// Reproduit : res/layout/itemligne2.xml (conteneur paddingLeft/Right 7dp, ligne2 → ligne5).
struct HomeLigneRow: View {
    let item: HomeLigne2Item
    let index: Int

    private func cite(_ k: Int) -> Cite {
        let all = MockData.allCites
        return all[(index * 3 + k) % all.count]
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HomeLigne2Section(item: item, cite: cite).modifier(HomeFadeIn(duration: 1.0))
            HomeLigne3Section(cite: cite).modifier(HomeFadeIn(duration: 0.7))
            HomeLigne4Section(cite: cite).modifier(HomeFadeIn(duration: 0.7))
            HomeLigne5Section().modifier(HomeFadeIn(duration: 0.7))
        }
        .padding(.horizontal, 7)
    }
}

/// En-tête d'une ligne : titre 15sp gras + "tout voir" 13sp black_50.
struct HomeLigneHeader: View {
    let title: String

    var body: some View {
        HStack(spacing: 0) {
            HomeText(title, 15, DS.black, bold: true)
                .frame(maxWidth: .infinity, alignment: .leading)
            HomeText("tout voir", 13, DS.blackA(50), bold: true)
        }
        .padding(.horizontal, 7)
        .padding(.bottom, 15)
    }
}
