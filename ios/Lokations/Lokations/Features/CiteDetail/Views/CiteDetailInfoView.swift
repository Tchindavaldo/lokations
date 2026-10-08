import SwiftUI

// Reproduit res/layout/fragment_detail.xml (fragment_detail.kt) : fond blanc,
// marges 5/35/5/56, en-tête « Caracteristique », onglets Details/Avantages/
// Inconvenient/contact, contenu défilant, barre Reserver / prix / Louer.
// Apparition en fondu 1000 ms (alpha 0 -> 1). marginTop 38dp = zone sûre iOS.
// Corrections (R13) : onglets actifs (défilement vers la section, contact -> appel),
// description de la cité, points blancs invisibles rendus visibles, Reserver/Louer
// ouvrent le paiement.

struct CiteDetailInfoView: View {
    let info: CiteDetailInfo
    let onReserve: () -> Void
    @Environment(\.openURL) private var openURL
    @State private var alpha: Double = 1
    @State private var tab = 0

    var body: some View {
        VStack(spacing: 0) {
            CiteDetailHeader(info: info, chip: "Caracteristique")
            ScrollViewReader { proxy in
                tabs { index in
                    if index == 3 {
                        if let url = info.telURL { openURL(url) }
                        return
                    }
                    withAnimation(.easeInOut(duration: 0.25)) { tab = index }
                    withAnimation { proxy.scrollTo(index, anchor: .top) }
                }
                .padding(.top, 25)
                ScrollView { content }
            }
            bottomBar
        }
        .padding(.horizontal, 5)
        .padding(.top, 35)
        .padding(.bottom, 56)
        .background(DS.white)
        .opacity(alpha)
        
    }

    // MARK: top_nav_detail (chaîne « spread »)

    private func tabs(_ select: @escaping (Int) -> Void) -> some View {
        let titles = ["Details", "Avantages", "Inconvenient", "contact"]
        return HStack(spacing: 0) {
            Spacer(minLength: 0)
            ForEach(titles.indices, id: \.self) { i in
                Button { select(i) } label: {
                    Text(titles[i])
                        .font(.system(size: 16, weight: tab == i ? .bold : .regular))
                        .foregroundStyle(tab == i ? DS.black : DS.blackA(50))
                        .overlay(alignment: .bottom) {
                            if tab == i {
                                Rectangle().fill(DS.black).frame(width: 80, height: 2).offset(y: 6)
                            }
                        }
                }
                .buttonStyle(.plain)
                Spacer(minLength: 0)
            }
        }
        .padding(.top, 5)
        .padding(.bottom, 6)
    }

    // MARK: ScrollView

    private var content: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .top) {
                Text(info.itemCategorie)
                    .font(.system(size: 14, weight: .bold))
                    .foregroundStyle(DS.black)
                    .padding(.leading, 15)
                Spacer(minLength: 0)
                HStack(spacing: 5) {
                    dot(DS.black); dot(DS.blackA(20)); dot(DS.blackA(20))
                }
                .padding(.trailing, 15)
            }
            .padding(.top, 25)
            .id(0)

            HStack(alignment: .top, spacing: 0) {
                measure("Longueur", "2.5m", centered: true).padding(.trailing, 50)
                measure("Largeur", "1.8m")
                measure("Hauteur", "1.94m").padding(.leading, 50)
            }
            .padding(.leading, 15)
            .padding(.top, 20)

            Text("Description")
                .font(.system(size: 14, weight: .bold))
                .foregroundStyle(DS.black)
                .padding(.leading, 15)
                .padding(.top, 35)
                .id(2)

            Text(info.cite.summary)
                .font(.system(size: 13))
                .foregroundStyle(DS.blackA(50))
                .padding(.leading, 15)
                .padding(.trailing, 5)
                .frame(maxWidth: .infinity, alignment: .leading)
                .overlay(alignment: .bottomTrailing) {
                    HStack(spacing: 5) {
                        dot(DS.blackA(20)); dot(DS.blackA(20)); dot(DS.black); dot(DS.blackA(20))
                    }
                    .padding(.trailing, 15)
                }
                .padding(.trailing, 15)
                .padding(.top, 10)

            HStack(spacing: 50) {
                bold("Inclus"); bold("Energy")
            }
            .padding(.leading, 15)
            .padding(.top, 35)
            .id(1)

            VStack(spacing: 35) {
                inclus("1.", "Internet ")
                inclus("2.", "coin penderie ")
                inclus("3.", "coin couisine ")
                inclus("4.", "Abonnement cable / canalsat ")
            }
            .padding(.top, 25)
        }
    }

    /// round_white (noir r15) / round_white_50 (blanc r50) en 8x8, marginLeft 5.
    private func dot(_ color: Color) -> some View {
        Circle().fill(color).frame(width: 8, height: 8)
    }

    private func bold(_ text: String) -> some View {
        Text(text).font(.system(size: 14, weight: .bold)).foregroundStyle(DS.black)
    }

    private func measure(_ label: String, _ value: String, centered: Bool = false) -> some View {
        VStack(alignment: centered ? .center : .leading, spacing: 0) {
            Text(label).font(.system(size: 14)).foregroundStyle(DS.blackA(70))
            Text(value).font(.system(size: 14)).foregroundStyle(DS.blackA(50))
        }
    }

    private func inclus(_ num: String, _ label: String) -> some View {
        ZStack(alignment: .leading) {
            Text(num).foregroundStyle(DS.blackA(50))
            Text(label).foregroundStyle(DS.blackA(50)).padding(.leading, 25)
            Text("oui ")
                .foregroundStyle(DS.black)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .padding(.trailing, 15)
        }
        .font(.system(size: 14))
        .padding(.leading, 15)
    }

    // MARK: bottom_lpr

    private var bottomBar: some View {
        HStack(spacing: 50) {
            Button("Reserver", action: onReserve).buttonStyle(CiteDetailChipStyle())
            action(info.prix)
            Button("Louer", action: onReserve).buttonStyle(CiteDetailChipStyle())
        }
        .frame(maxWidth: .infinity)
        .frame(height: 50)
    }

    private func action(_ text: String) -> some View {
        CiteDetailChip(text: text, size: 14, color: DS.black, bold: true,
                       background: DS.android(0xE6E0E3), radius: 10, h: 7, v: 5)
    }
}

/// Bouton au style round_e6e0e3_10 (14sp bold noir, padding 7/5).
struct CiteDetailChipStyle: ButtonStyle {
    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .font(.system(size: 14, weight: .bold))
            .foregroundStyle(DS.black)
            .padding(.horizontal, 7)
            .padding(.vertical, 5)
            .background(DS.android(0xE6E0E3), in: RoundedRectangle(cornerRadius: 10))
            .opacity(configuration.isPressed ? 0.6 : 1)
    }
}
