import SwiftUI

// Reproduit : res/layout/inflate_chambre_infos.xml (adapteur_recycleView_chambre_info.kt,
// @id/item = nom du produit Firestore). Le bloc 0dp x 0dp du XML n'est pas visible.
struct BoutiqueChambreInfoRow: View {
    let title: String

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .center, spacing: 0) {
                Image("m7")
                    .resizable()
                    .scaledToFill()
                    .frame(width: 90, height: 85)
                    .clipShape(RoundedRectangle(cornerRadius: 15))

                VStack(alignment: .leading, spacing: 0) {
                    Text(title)
                        .font(.system(size: 12, weight: .bold))
                        .foregroundStyle(DS.black)
                        .padding(.bottom, 5)
                    Spacer(minLength: 0)
                    HStack(spacing: 0) {
                        small("loyer")
                        value("400 000/Ans").padding(.leading, 5)
                    }
                    .padding(.bottom, 4)
                    HStack(spacing: 0) {
                        small("Payer")
                        value("400 000").padding(.leading, 5)
                        Text("Impayé").font(.system(size: 10)).foregroundStyle(DS.black).padding(.leading, 8)
                        value("00 000f").padding(.leading, 5)
                    }
                    .padding(.bottom, 4)
                    HStack(spacing: 0) {
                        small("Locataire")
                        value("Tchinda Valdo blair").padding(.leading, 5)
                    }
                    .padding(.bottom, 5)
                }
                .padding(.leading, 5)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)

                VStack(spacing: 0) {
                    Text("Occupé")
                        .font(.system(size: 11, weight: .bold))
                        .foregroundStyle(DS.android(0x004288))
                    Spacer(minLength: 0)
                    VStack(spacing: 0) {
                        Text("FIn Contrat")
                            .font(.system(size: 9, weight: .bold))
                            .foregroundStyle(DS.blackA(50))
                        Text("05 - 08 - 2025")
                            .font(.system(size: 9, weight: .bold))
                            .foregroundStyle(DS.black)
                            .padding(.top, 5)
                    }
                    .padding(.horizontal, 7)
                    .padding(.vertical, 5)
                    .background(RoundedRectangle(cornerRadius: 15).fill(DS.blackA(5)))
                }
                .frame(maxHeight: .infinity)
            }
            .frame(height: 85)

            HStack(spacing: 5) {
                chip("rappel")
                chip("Contacter")
                chip("Modifier")
                chip("Résilier")
            }
            .padding(.top, 5)
        }
        .padding(.horizontal, 5)
        .padding(.bottom, 20)
        .background(DS.white)
    }

    private func small(_ text: String) -> some View {
        Text(text).font(.system(size: 9)).foregroundStyle(DS.black)
    }

    private func value(_ text: String) -> some View {
        Text(text).font(.system(size: 10, weight: .bold)).foregroundStyle(DS.blackA(50))
    }

    private func chip(_ text: String) -> some View {
        Text(text)
            .font(.system(size: 9))
            .foregroundStyle(DS.black)
            .padding(.horizontal, 7)
            .padding(.vertical, 3)
            .background(RoundedRectangle(cornerRadius: 15).fill(DS.blackA(10)))
    }
}
