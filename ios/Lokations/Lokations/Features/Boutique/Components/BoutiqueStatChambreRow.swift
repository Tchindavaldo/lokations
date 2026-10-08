import SwiftUI

// Reproduit : res/layout/inflate_statistique_chambre.xml
// (adapteur_recycleView_statistique_chambre.kt, data_periodique_statistique_chambre.kt).
struct BoutiqueStatData: Identifiable {
    let id = UUID()
    let periode: String
    let date: String
    let visiteSurApp: Int, evoluVisiteSurApp: Int
    let visiteSurApplm: Int, evoluVisiteSurApplm: Int
    let comPosi: Int, evoluComPosi: Int
    let comNeg: Int, evoluComNeg: Int
    let classementApp: Int, evoluClassementApp: Int
    let classementRech: Int, evoluClassementRech: Int
    let likePosi: Int, likeNeg: Int
    let note: Int, nbrVote: Int

    /// Donnees en dur de Fragment_statistique_chambre.kt.
    static let samples: [BoutiqueStatData] = [
        .init(periode: "jour 1", date: "03-06-2024",
              visiteSurApp: 13, evoluVisiteSurApp: 11, visiteSurApplm: 12, evoluVisiteSurApplm: 19,
              comPosi: 11, evoluComPosi: 1, comNeg: 3, evoluComNeg: 1,
              classementApp: 51, evoluClassementApp: 11, classementRech: 11, evoluClassementRech: 4,
              likePosi: 11, likeNeg: 1, note: 65, nbrVote: 4931),
        .init(periode: "jour 2", date: "04-06-2024",
              visiteSurApp: 23, evoluVisiteSurApp: 21, visiteSurApplm: 22, evoluVisiteSurApplm: 29,
              comPosi: 21, evoluComPosi: 12, comNeg: 2, evoluComNeg: 2,
              classementApp: 21, evoluClassementApp: 12, classementRech: 21, evoluClassementRech: 24,
              likePosi: 211, likeNeg: 2, note: 85, nbrVote: 2931),
        .init(periode: "jour 3", date: "05-06-2024",
              visiteSurApp: 13, evoluVisiteSurApp: 11, visiteSurApplm: 12, evoluVisiteSurApplm: 19,
              comPosi: 11, evoluComPosi: 1, comNeg: 3, evoluComNeg: 1,
              classementApp: 51, evoluClassementApp: 11, classementRech: 11, evoluClassementRech: 4,
              likePosi: 11, likeNeg: 1, note: 65, nbrVote: 4931)
    ]
}

struct BoutiqueStatChambreRow: View {
    let data: BoutiqueStatData

    private let green = DS.android(0xCC03FF25, hasAlpha: true)
    private let red = DS.android(0xCCFF0303, hasAlpha: true)

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .bottom, spacing: 0) {
                Text(data.periode)
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                    .padding(.leading, 10)
                Spacer(minLength: 0)
                Text(data.date)
                    .font(.system(size: 12))
                    .foregroundStyle(DS.blackA(50))
                    .padding(.trailing, 10)
            }

            HStack(alignment: .top, spacing: 15) {
                visiteBox
                commentaireBox.frame(maxWidth: .infinity, maxHeight: .infinity)
            }
            .fixedSize(horizontal: false, vertical: true)
            .padding(.top, 15)
            .padding(.horizontal, 10)

            notes.padding(.top, 10).padding(.leading, 10)
            classement.padding(.top, 10).padding(.leading, 10)

            Rectangle()
                .fill(DS.black)
                .frame(height: 1)
                .padding(.trailing, 15)
                .padding(.top, 25)
        }
        .background(DS.white)
    }

    // MARK: item1_page_visibilite
    private var visiteBox: some View {
        VStack(alignment: .leading, spacing: 0) {
            chip("Visite").padding(.top, 5)
            label("Sur l'application").padding(.top, 10)
            HStack(spacing: 10) {
                bold("\(data.visiteSurApp)", DS.black)
                bold("\(data.evoluVisiteSurApp)", green)
            }
            label("Sur L'emplacement").padding(.top, 10)
            HStack(spacing: 10) {
                bold("\(data.visiteSurApplm)", DS.black)
                bold("\(data.evoluVisiteSurApplm)", red)
            }
            .padding(.bottom, 10)
        }
        .padding(.leading, 5)
        .padding(.trailing, 30)
        .background(RoundedRectangle(cornerRadius: 15).fill(DS.blackA(10)))
    }

    // MARK: item2_page_visibilite
    private var commentaireBox: some View {
        VStack(alignment: .leading, spacing: 0) {
            chip("Commentaire").padding(.top, 5)
            Spacer(minLength: 0)
            HStack(alignment: .bottom, spacing: 0) {
                label("Positif")
                Spacer(minLength: 0)
                bold("\(data.comPosi)", DS.black).padding(.trailing, 10)
                bold("\(data.evoluComPosi)", red)
            }
            HStack(alignment: .bottom, spacing: 0) {
                label("Negatif")
                Spacer(minLength: 0)
                bold("\(data.comNeg)", DS.black).padding(.trailing, 10)
                bold("\(data.evoluComNeg)", green)
            }
            .padding(.top, 12)
            .padding(.bottom, 15)
        }
        .padding(.leading, 5)
        .padding(.trailing, 10)
        .background(RoundedRectangle(cornerRadius: 15).fill(DS.blackA(10)))
    }

    // MARK: item3_page_statistique
    private var notes: some View {
        HStack(alignment: .top, spacing: 0) {
            HStack(spacing: 0) {
                like("\(data.likePosi)", icon: "ic_baseline_thumb_up_off_alt_24")
                like("\(data.likeNeg)", icon: "ic_baseline_thumb_down_off_alt_24").padding(.leading, 8)
            }
            Spacer(minLength: 0)
            VStack(alignment: .trailing, spacing: 0) {
                HStack(spacing: 0) {
                    Text("Notes")
                        .font(.system(size: 14, weight: .bold))
                        .foregroundStyle(DS.black)
                        .padding(.trailing, 5)
                    label("Sur l'application")
                    Image("fv").resizable().frame(width: 18, height: 18).padding(.leading, 3)
                }
                HStack(spacing: 0) {
                    Text("\(data.note)").font(.system(size: 13)).foregroundStyle(DS.android(0xFFE082))
                    label("/100 ").padding(.trailing, 10)
                    Text("\(data.nbrVote)").font(.system(size: 14)).foregroundStyle(DS.black)
                    Text(" Votes ").font(.system(size: 14)).foregroundStyle(DS.black)
                }
            }
            .padding(.trailing, 10)
        }
    }

    // MARK: item4_page_statistique
    private var classement: some View {
        HStack(alignment: .top, spacing: 0) {
            ZStack {
                RoundedRectangle(cornerRadius: 15).fill(DS.android(0xE6E0E3))
                Image("ic_baseline_trending_up_24").resizable().scaledToFill().frame(width: 24, height: 24)
            }
            .frame(width: 55, height: 55)

            VStack(alignment: .leading, spacing: 0) {
                Text("classement")
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                Spacer(minLength: 0)
                rank("Classement Dans Les Recherches", data.classementRech, data.evoluClassementRech)
                rank("Classement sur L'application", data.classementApp, data.evoluClassementApp)
            }
            .padding(.leading, 5)
            .padding(.trailing, 10)
            .frame(height: 55)
        }
    }

    // MARK: Briques
    private func chip(_ text: String) -> some View {
        Text(text)
            .font(.system(size: 12))
            .foregroundStyle(DS.white)
            .padding(3)
            .background(Capsule().fill(DS.black))
    }

    private func label(_ text: String) -> some View {
        Text(text).font(.system(size: 12)).foregroundStyle(DS.blackA(50))
    }

    private func bold(_ text: String, _ color: Color) -> some View {
        Text(text).font(.system(size: 14, weight: .bold)).foregroundStyle(color)
    }

    private func like(_ count: String, icon: String) -> some View {
        HStack(spacing: 0) {
            label(count)
            Image(icon).resizable().frame(width: 18, height: 18).padding(.leading, 3)
        }
    }

    private func rank(_ title: String, _ value: Int, _ evolution: Int) -> some View {
        HStack(alignment: .bottom, spacing: 0) {
            label(title)
            Spacer(minLength: 0)
            bold("\(value)", DS.black).padding(.trailing, 10)
            bold("\(evolution)", green)
        }
    }
}
