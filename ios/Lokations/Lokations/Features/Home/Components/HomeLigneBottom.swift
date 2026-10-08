import SwiftUI

// Reproduit : res/layout/itemligne2.xml — ligne4 ("Top qualité", 3 cartes 150x230)
// et ligne5 ("En cours de construction", ViewPager 175dp + barre d'actions).

struct HomeLigne4Section: View {
    let cites: [Cite]
    let all: [Cite]

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HomeLigneHeader(title: "Top qualité", cites: all)
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 15) {
                    ForEach(cites.indices, id: \.self) { i in
                        NavigationLink(value: cites[i]) { HomeLigne4Card(cite: cites[i]) }
                    }
                }
                .buttonStyle(.plain)
            }
        }
        .padding(.bottom, 35)
    }
}

struct HomeLigne4Card: View {
    let cite: Cite

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            ZStack(alignment: .bottom) {
                Image(cite.imageName).resizable().scaledToFill()
                    .frame(width: 150, height: 230).clipped()
                VStack(alignment: .leading, spacing: 0) {
                    HStack(spacing: 0) {
                        HomeText(cite.name, 10, DS.white, bold: true)
                    }
                    HomeText("\(cite.freeRooms) chambres disponibles", 10, DS.whiteA(70)).padding(.top, 3)
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.leading, 7).padding(.top, 3).padding(.bottom, 2)
                .background(DS.blackA(80))
            }
            .frame(width: 150, height: 230)
            .clipShape(RoundedRectangle(cornerRadius: 10))
            .background(RoundedRectangle(cornerRadius: 10).fill(DS.blackA(5)))
            HStack(alignment: .bottom, spacing: 0) {
                HStack(alignment: .firstTextBaseline, spacing: 0) {
                    HomeText(homePrice(cite.pricePerMonth), 10, DS.black, bold: true)
                    HomeText(" /", 8, DS.blackA(75), bold: true)
                    HomeText("Mois", 8, DS.blackA(75), bold: true)
                }
                .padding(.leading, 5)
                .frame(maxWidth: .infinity, alignment: .leading)
                HomeScoreBadge(fill: DS.black, trailing: 4).padding(.trailing, 3)
                HStack(spacing: 0) {
                    HomeText(cite.district, 8, DS.white)
                    Image("plce").resizable().scaledToFit().frame(width: 12, height: 12)
                }
                .padding(.leading, 4).padding(.top, 1).padding(.trailing, 2).padding(.bottom, 1)
                .background(Capsule().fill(DS.black))
            }
            .padding(.top, 5)
        }
        .frame(width: 150)
    }
}

struct HomeLigne5Section: View {
    let cite: Cite
    @EnvironmentObject private var favorites: FavoritesStore
    @State private var showNotifications = false

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(spacing: 0) {
                HomeText("En cours de construction", 15, DS.black, bold: true)
                    .frame(maxWidth: .infinity, alignment: .leading)
                Button { showNotifications = true } label: {
                    HStack(spacing: 0) {
                    HomeText("Notification", 13, DS.blackA(50), bold: true).padding(.trailing, 3)
                    Image("ic_baseline_notifications_none_24").resizable().scaledToFit()
                        .frame(width: 18, height: 18)
                    }
                }
                .buttonStyle(.plain)
            }
            HomeSlidePager(slides: cite.gallery.map { HomeSlide(cite: cite, image: $0) })
                .frame(height: 175)
                .background(RoundedRectangle(cornerRadius: 10).fill(DS.blackA(5)))
                .padding(.top, 15)
            HStack(spacing: 0) {
                HomeSlideDots().frame(maxWidth: .infinity, alignment: .leading)
                HStack(spacing: 0) {
                    HomeText("300", 9, DS.blackA(50))
                    Image("ic_baseline_thumb_up_off_alt_24").resizable().scaledToFit()
                        .frame(width: 13, height: 13).padding(.leading, 3)
                    HomeText("50", 9, DS.blackA(50)).padding(.leading, 8)
                    Image("ic_baseline_thumb_down_off_alt_24").resizable().scaledToFit()
                        .frame(width: 13, height: 13).padding(.leading, 3)
                }
                .padding(.trailing, 5)
                ZStack {
                    Circle().fill(DS.black)
                    Image("fv").resizable().scaledToFit().frame(width: 6, height: 10)
                }
                .frame(width: 10, height: 10)
                .padding(.horizontal, 3)
                HomeText("4.5", 10, DS.black).padding(.trailing, 5)
                Button { favorites.toggle(cite) } label: {
                    Image("favoris").resizable().scaledToFit().frame(width: 13, height: 13)
                        .opacity(favorites.contains(cite) ? 1 : 0.4)
                }
                .buttonStyle(.plain)
                .padding(.trailing, 5)
                NavigationLink(value: cite) {
                    HomeText("reserver", 10, DS.white)
                        .padding(.vertical, 4).padding(.horizontal, 12)
                        .background(RoundedRectangle(cornerRadius: 10).fill(DS.black))
                }
                .buttonStyle(.plain)
            }
            .padding(.top, 5)
            .padding(.horizontal, 1)
        }
        .padding(.bottom, 35)
        .sheet(isPresented: $showNotifications) { NotificationsView() }
    }
}
