import SwiftUI

// Reproduit : res/layout/fragment_home.xml — Toolbar (round.xml, search, pps, notif, ccount2)
// et LinearLayout top_icone_nav (TOUT, CHAMBRE, MAISON, APPART, STUDIO, HOTEL).
struct HomeToolbar: View {
    let onSearch: (String) -> Void
    let onCart: () -> Void
    let onNotifications: () -> Void
    let onAccount: () -> Void
    @State private var query = ""

    var body: some View {
        HStack(spacing: 0) {
            HStack(spacing: 0) {
                ZStack(alignment: .bottom) {
                    TextField("", text: $query)
                        .font(.system(size: 18))
                        .foregroundStyle(DS.black)
                        .submitLabel(.search)
                        .onSubmit { onSearch(query) }
                        .padding(.horizontal, 4)
                        .frame(maxHeight: .infinity)
                    // Soulignement par défaut de l'EditText Android
                    Rectangle().fill(DS.blackA(54)).frame(height: 1)
                        .padding(.horizontal, 4).padding(.bottom, 7)
                }
                .frame(width: 135)
                Button { onSearch(query) } label: {
                    Image("search").resizable().scaledToFit().frame(width: 20)
                }
            }
            .frame(width: 155, height: 35, alignment: .leading)
            .background(RoundedRectangle(cornerRadius: 27).fill(DS.android(0x4DB18484, hasAlpha: true)))
            Spacer(minLength: 0)
            Button(action: onCart) {
                Image("pps").resizable().scaledToFit().frame(width: 20, height: 30)
            }
            .padding(.trailing, 8)
            Button(action: onNotifications) {
                Image("notif").resizable().scaledToFit().frame(width: 20, height: 30)
            }
            .padding(.trailing, 8)
            Button(action: onAccount) {
                Image("ccount2").resizable().scaledToFit().frame(width: 35, height: 35)
            }
            .padding(.trailing, 20)
        }
        .buttonStyle(.plain)
        .padding(.leading, 16)
        .frame(height: 56)
    }
}

/// Catégories : chacune ouvre la liste filtrée correspondante (aucun bouton inactif).
struct HomeCategoryNav: View {
    private let items: [(icon: String, title: String)] = [
        ("pps", "TOUT"), ("prix", "CHAMBRE"), ("photo", "MAISON"),
        ("comment", "APPART"), ("plce", "STUDIO"), ("photo", "HOTEL"),
    ]

    private func cites(for title: String) -> [Cite] {
        let all = MockData.allCites
        switch title {
        case "TOUT": return all
        case "CHAMBRE": return all.filter { $0.freeRooms > 0 }.sorted { $0.freeRooms > $1.freeRooms }
        default: return all.sorted { $0.pricePerMonth < $1.pricePerMonth }
        }
    }

    var body: some View {
        HStack(spacing: 15) {
            ForEach(items.indices, id: \.self) { i in
                NavigationLink(value: HomeRoute.list(title: items[i].title.capitalized,
                                                     cites: cites(for: items[i].title))) {
                    VStack(spacing: 1) {
                        Image(items[i].icon).resizable().scaledToFill()
                            .frame(width: 13, height: 13).clipped()
                        HomeText(items[i].title, 9, i == 0 ? DS.black : DS.blackA(50), bold: true)
                    }
                }
                .buttonStyle(.plain)
            }
        }
        .frame(maxWidth: .infinity)
    }
}
