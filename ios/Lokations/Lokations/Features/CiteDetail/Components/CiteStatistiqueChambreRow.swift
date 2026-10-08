import SwiftUI

// Reproduit res/layout/inflate_statistique_chambre.xml
// (adapteur_recycleView_statistique_chambre, données data_periodique_statistique_chambre).

struct CiteStatistiqueChambreData: Identifiable {
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

    /// Fragment_statistique_chambre.kt
    static let demo: [CiteStatistiqueChambreData] = [
        .init("jour 1", "03-06-2024", [13, 11, 12, 19, 11, 1, 3, 1, 51, 11, 11, 4, 11, 1, 65, 4931]),
        .init("jour 2", "04-06-2024", [23, 21, 22, 29, 21, 12, 2, 2, 21, 12, 21, 24, 211, 2, 85, 2931]),
        .init("jour 3", "05-06-2024", [13, 11, 12, 19, 11, 1, 3, 1, 51, 11, 11, 4, 11, 1, 65, 4931]),
    ]

    private init(_ periode: String, _ date: String, _ v: [Int]) {
        self.periode = periode
        self.date = date
        visiteSurApp = v[0]; evoluVisiteSurApp = v[1]
        visiteSurApplm = v[2]; evoluVisiteSurApplm = v[3]
        comPosi = v[4]; evoluComPosi = v[5]
        comNeg = v[6]; evoluComNeg = v[7]
        classementApp = v[8]; evoluClassementApp = v[9]
        classementRech = v[10]; evoluClassementRech = v[11]
        likePosi = v[12]; likeNeg = v[13]
        note = v[14]; nbrVote = v[15]
    }
}

struct CiteStatistiqueChambreRow: View {
    let data: CiteStatistiqueChambreData

    private let green = DS.android(0xCC03FF25, hasAlpha: true)
    private let red = DS.android(0xCCFF0303, hasAlpha: true)

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .lastTextBaseline) {
                Text(data.periode).font(.system(size: 12, weight: .bold)).foregroundStyle(DS.black)
                Spacer(minLength: 0)
                Text(data.date).font(.system(size: 12)).foregroundStyle(DS.blackA(50))
            }
            .padding(.horizontal, 10)

            HStack(spacing: 15) {
                visites
                commentaires
            }
            .padding(.leading, 10)
            .padding(.trailing, 10)
            .padding(.top, 15)

            likesEtNotes.padding(.leading, 10).padding(.top, 10)
            classement.padding(.leading, 10).padding(.top, 10)

            Rectangle().fill(DS.black).frame(height: 1).padding(.top, 25)
        }
        .padding(.bottom, 20)
        .background(DS.white)
    }

    private func chip(_ text: String) -> some View {
        Text(text)
            .font(.system(size: 12))
            .foregroundStyle(DS.white)
            .padding(3)
            .background(DS.black, in: Capsule())
            .padding(.leading, 5)
            .padding(.top, 5)
    }

    private func pair(_ a: Int, _ b: Int, _ color: Color) -> some View {
        HStack(spacing: 10) {
            Text("\(a)").foregroundStyle(DS.black)
            Text("\(b)").foregroundStyle(color)
        }
        .font(.system(size: 14, weight: .bold))
    }

    private func small(_ text: String) -> some View {
        Text(text).font(.system(size: 12)).foregroundStyle(DS.blackA(50))
    }

    // item1_page_visibilite
    private var visites: some View {
        VStack(alignment: .leading, spacing: 0) {
            chip("Visite")
            VStack(alignment: .leading, spacing: 0) {
                small("Sur l'application").padding(.top, 10)
                pair(data.visiteSurApp, data.evoluVisiteSurApp, green)
                small("Sur L'emplacement").padding(.top, 10)
                pair(data.visiteSurApplm, data.evoluVisiteSurApplm, red).padding(.bottom, 10)
            }
            .padding(.leading, 5)
        }
        .padding(.trailing, 30)
        .background(DS.blackA(10), in: RoundedRectangle(cornerRadius: 15))
    }

    // item2_page_visibilite
    private var commentaires: some View {
        VStack(alignment: .leading, spacing: 0) {
            chip("Commentaire")
            Spacer(minLength: 0)
            HStack(alignment: .lastTextBaseline) {
                small("Positif").padding(.leading, 5)
                Spacer(minLength: 0)
                pair(data.comPosi, data.evoluComPosi, red).padding(.trailing, 10)
            }
            Spacer(minLength: 12)
            HStack(alignment: .lastTextBaseline) {
                small("Negatif").padding(.leading, 5)
                Spacer(minLength: 0)
                pair(data.comNeg, data.evoluComNeg, green).padding(.trailing, 10)
            }
            .padding(.bottom, 15)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
        .background(DS.blackA(10), in: RoundedRectangle(cornerRadius: 15))
    }

    // item3_page_statistique
    private var likesEtNotes: some View {
        HStack(alignment: .top) {
            HStack(spacing: 8) {
                like(data.likePosi, "ic_baseline_thumb_up_off_alt_24")
                like(data.likeNeg, "ic_baseline_thumb_down_off_alt_24")
            }
            Spacer(minLength: 0)
            VStack(alignment: .trailing, spacing: 0) {
                HStack(spacing: 0) {
                    Text("Notes").font(.system(size: 14, weight: .bold)).foregroundStyle(DS.black)
                        .padding(.trailing, 5)
                    small("Sur l'application")
                    Image("fv").resizable().frame(width: 18, height: 18).padding(.leading, 3)
                }
                HStack(alignment: .firstTextBaseline, spacing: 0) {
                    Text("\(data.note)").font(.system(size: 13)).foregroundStyle(DS.android(0xFFE082))
                    small("/100 ").padding(.trailing, 10)
                    Text("\(data.nbrVote)  ").font(.system(size: 14)).foregroundStyle(DS.black)
                    Text(" Votes ").font(.system(size: 14)).foregroundStyle(DS.black)
                }
            }
            .padding(.trailing, 10)
        }
    }

    private func like(_ value: Int, _ icon: String) -> some View {
        HStack(spacing: 3) {
            small("\(value)")
            Image(icon).resizable().frame(width: 18, height: 18)
        }
    }

    // item4_page_statistique
    private var classement: some View {
        HStack(spacing: 5) {
            Image("ic_baseline_trending_up_24")
                .resizable()
                .scaledToFill()
                .frame(width: 24, height: 24)
                .frame(width: 55, height: 55)
                .background(DS.android(0xE6E0E3), in: RoundedRectangle(cornerRadius: 15))

            VStack(alignment: .leading, spacing: 0) {
                Text("classement").font(.system(size: 12, weight: .bold)).foregroundStyle(DS.black)
                Spacer(minLength: 0)
                HStack(alignment: .lastTextBaseline) {
                    small("Classement Dans Les Recherches")
                    Spacer(minLength: 0)
                    pair(data.classementRech, data.evoluClassementRech, green)
                }
                HStack(alignment: .lastTextBaseline) {
                    small("Classement sur L'application")
                    Spacer(minLength: 0)
                    pair(data.classementApp, data.evoluClassementApp, green)
                }
            }
            .padding(.trailing, 10)
            .frame(height: 55)
        }
    }
}
