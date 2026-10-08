import SwiftUI

// Reproduit res/layout/fragment_comment.xml (fragment_comment.kt) : en-tête
// « Commentaire », filtres Negatif / Positif, liste défilante de commentaires et
// champ « Entrer votre commentaire ici ». marginTop 38dp = zone sûre iOS.

struct CiteCommentView: View {
    @State private var draft = "Entrer votre commentaire ici"

    private struct Row: Identifiable {
        let id = UUID()
        let image: String?
        let name: String
        let top: CGFloat
    }

    private let rows: [Row] = [
        Row(image: "m6", name: "Rosny", top: 40),
        Row(image: "m7", name: "ivant leopol", top: 40),
        Row(image: "m4", name: "merly", top: 45),
        Row(image: "m7", name: "john", top: 45),
        Row(image: "m6", name: "rames", top: 45),
        Row(image: "m4", name: "dany Yan", top: 45),
        Row(image: "m88", name: "Cebastien N", top: 45),
        Row(image: nil, name: "rudolf ryan", top: 45),
    ]

    var body: some View {
        VStack(spacing: 0) {
            CiteDetailHeader(chip: "Commentaire")

            HStack(alignment: .firstTextBaseline, spacing: 15) {
                CiteDetailChip(text: "Negatif", size: 12, color: DS.black, bold: true,
                               background: DS.android(0xE6E0E3), radius: 10, h: 7, v: 5)
                Text("Positif").font(.system(size: 12)).foregroundStyle(DS.blackA(50))
                Spacer(minLength: 0)
            }
            .padding(.leading, 15)
            .padding(.top, 30)

            ScrollView {
                VStack(spacing: 0) {
                    ForEach(rows) { row in
                        commentRow(row).padding(.top, row.top)
                    }
                }
            }
            .padding(.bottom, 25)

            TextField("", text: $draft)
                .font(.system(size: 12))
                .foregroundStyle(DS.android(0xF8000000, hasAlpha: true))
                .padding(.leading, 15)
                .frame(height: 25)
                .background(DS.android(0xB2FFFFFF, hasAlpha: true))
                .clipShape(Capsule())
                .padding(.leading, 5)
                .padding(.trailing, 70)
                .padding(.leading, 25)
                .padding(.trailing, 35)
                .padding(.bottom, 10)
        }
        .padding(.horizontal, 5)
        .padding(.horizontal, 5)
        .padding(.top, 30)
        .padding(.bottom, 56)
        .background(DS.white)
    }

    private func commentRow(_ row: Row) -> some View {
        HStack(spacing: 0) {
            Group {
                if let image = row.image {
                    Image(image).resizable().scaledToFill()
                        .background(DS.whiteA(50))
                } else {
                    // round_e6e0e3_55 + ic_baseline_shopping_bag_24 (24dp)
                    Image("ic_baseline_shopping_bag_24").resizable().scaledToFill()
                        .frame(width: 24, height: 24)
                        .frame(width: 55, height: 55)
                        .background(DS.android(0xE6E0E3))
                }
            }
            .frame(width: 55, height: 55)
            .clipShape(Circle())

            VStack(alignment: .leading, spacing: 0) {
                Text(row.name)
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                Spacer(minLength: 0)
                Text("construction terminé")
                    .font(.system(size: 12))
                    .foregroundStyle(DS.blackA(50))
                Text("de la cité des anges à Douala au quartier bonapriso")
                    .font(.system(size: 12))
                    .foregroundStyle(DS.blackA(50))
            }
            .padding(.leading, 15)
            .padding(.bottom, 2)
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
        }
        .frame(height: 55)
        .padding(.leading, 15)
        .padding(.trailing, 25)
    }
}
