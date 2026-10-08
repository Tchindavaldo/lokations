import SwiftUI

// Reproduit : res/layout/fragment_home.xml — Toolbar (round.xml, search, pps, notif, ccount2)
// et LinearLayout top_icone_nav (TOUT, CHAMBRE, MAISON, APPART, STUDIO, HOTEL).
struct HomeToolbar: View {
    @State private var query = ""

    var body: some View {
        HStack(spacing: 0) {
            HStack(spacing: 0) {
                ZStack(alignment: .bottom) {
                    TextField("", text: $query)
                        .font(.system(size: 18))
                        .foregroundStyle(DS.black)
                        .padding(.horizontal, 4)
                        .frame(maxHeight: .infinity)
                    // Soulignement par défaut de l'EditText Android
                    Rectangle().fill(DS.blackA(54)).frame(height: 1)
                        .padding(.horizontal, 4).padding(.bottom, 7)
                }
                .frame(width: 135)
                Image("search").resizable().scaledToFit().frame(width: 20)
            }
            .frame(width: 155, height: 35, alignment: .leading)
            .background(RoundedRectangle(cornerRadius: 27).fill(DS.android(0x4DB18484, hasAlpha: true)))
            Spacer(minLength: 0)
            Image("pps").resizable().scaledToFit().frame(width: 20, height: 30).padding(.trailing, 8)
            Image("notif").resizable().scaledToFit().frame(width: 20, height: 30).padding(.trailing, 8)
            Image("ccount2").resizable().scaledToFit().frame(width: 35, height: 35).padding(.trailing, 20)
        }
        .padding(.leading, 16)
        .frame(height: 56)
    }
}

struct HomeCategoryNav: View {
    private let items: [(icon: String, title: String)] = [
        ("pps", "TOUT"), ("prix", "CHAMBRE"), ("photo", "MAISON"),
        ("comment", "APPART"), ("plce", "STUDIO"), ("photo", "HOTEL"),
    ]

    var body: some View {
        HStack(spacing: 15) {
            ForEach(items.indices, id: \.self) { i in
                VStack(spacing: 1) {
                    Image(items[i].icon).resizable().scaledToFill()
                        .frame(width: 13, height: 13).clipped()
                    HomeText(items[i].title, 9, i == 0 ? DS.black : DS.blackA(50), bold: true)
                }
            }
        }
        .frame(maxWidth: .infinity)
    }
}
