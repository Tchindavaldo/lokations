import SwiftUI

// Reproduit res/layout/fragment_comment.xml (fragment_comment.kt) : en-tête
// « Commentaire », filtres Negatif / Positif, liste défilante de commentaires et
// champ « Entrer votre commentaire ici ». marginTop 38dp = zone sûre iOS.
// Corrections (R13) : filtres Negatif/Positif actifs, le champ est un vrai placeholder
// et l'envoi ajoute le commentaire à la liste.

struct CiteCommentView: View {
    let info: CiteDetailInfo

    @State private var draft = ""
    @State private var positif = true

    private struct Row: Identifiable {
        let id = UUID()
        let image: String?
        let name: String
        let text: String
        let positif: Bool
    }

    @State private var rows: [Row] = [
        Row(image: "m6", name: "Rosny", text: "Très propre et calme, je recommande.", positif: true),
        Row(image: "m7", name: "ivant leopol", text: "Bon rapport qualité/prix.", positif: true),
        Row(image: "m4", name: "merly", text: "Coupures d'eau fréquentes le week-end.", positif: false),
        Row(image: "m7", name: "john", text: "Gardiennage sérieux, quartier sûr.", positif: true),
        Row(image: "m6", name: "rames", text: "Connexion internet lente le soir.", positif: false),
        Row(image: "m4", name: "dany Yan", text: "Chambres spacieuses et lumineuses.", positif: true),
        Row(image: "m88", name: "Cebastien N", text: "Parking trop petit.", positif: false),
        Row(image: nil, name: "rudolf ryan", text: "Proche des commerces.", positif: true),
    ]

    private var visibles: [Row] { rows.filter { $0.positif == positif } }

    var body: some View {
        VStack(spacing: 0) {
            CiteDetailHeader(info: info, chip: "Commentaire")

            HStack(alignment: .firstTextBaseline, spacing: 15) {
                filtre("Negatif", actif: !positif) { positif = false }
                filtre("Positif", actif: positif) { positif = true }
                Spacer(minLength: 0)
            }
            .padding(.leading, 15)
            .padding(.top, 30)

            ScrollView {
                VStack(spacing: 0) {
                    ForEach(Array(visibles.enumerated()), id: \.element.id) { i, row in
                        commentRow(row).padding(.top, i < 2 ? 40 : 45)
                    }
                }
            }
            .padding(.bottom, 25)

            TextField("Entrer votre commentaire ici", text: $draft)
                .submitLabel(.send)
                .onSubmit(envoyer)
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
                Text(row.positif ? "Positif" : "Negatif")
                    .font(.system(size: 12))
                    .foregroundStyle(DS.blackA(50))
                Text(row.text).lineLimit(1)
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

    private func filtre(_ titre: String, actif: Bool, action: @escaping () -> Void) -> some View {
        Button(action: action) {
            Text(titre)
                .font(.system(size: 12, weight: actif ? .bold : .regular))
                .foregroundStyle(actif ? DS.black : DS.blackA(50))
                .padding(.horizontal, actif ? 7 : 0)
                .padding(.vertical, actif ? 5 : 0)
                .background(actif ? DS.android(0xE6E0E3) : DS.android(0x00FFFFFF, hasAlpha: true),
                            in: RoundedRectangle(cornerRadius: 10))
        }
        .buttonStyle(.plain)
    }

    private func envoyer() {
        let texte = draft.trimmingCharacters(in: .whitespacesAndNewlines)
        guard !texte.isEmpty else { return }
        rows.insert(Row(image: nil, name: "Moi", text: texte, positif: positif), at: 0)
        draft = ""
    }
}
