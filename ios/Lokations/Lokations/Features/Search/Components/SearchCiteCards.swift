import SwiftUI

/// Reproduit `res/layout/icon4.xml` (CustomAdapter2) : largeur 181, padding 7/7/bas 25,
/// image 110 coins 13, titre 12 gras, lignes 8 black_70, "fv  lv  ds" 9 black_50, "fv  lv" 9 black_70.
struct SearchCiteCardLarge: View {
    let cite: Cite

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Image(cite.imageName)
                .resizable()
                .scaledToFill()
                .frame(width: 167, height: 110)
                .clipShape(RoundedRectangle(cornerRadius: 13))
            HStack(alignment: .top, spacing: 0) {
                SearchCiteInfoColumn(cite: cite)
                Spacer(minLength: 0)
                VStack(alignment: .trailing, spacing: 0) {
                    Text(cite.district)
                        .font(.system(size: 12))
                        .foregroundStyle(DS.blackA(50))
                        .padding(.top, 4)
                    Spacer(minLength: 35)
                    Text("\(cite.pricePerMonth.formatted()) F")
                        .font(.system(size: 12))
                        .foregroundStyle(DS.blackA(70))
                }
            }
            .fixedSize(horizontal: false, vertical: true)
        }
        .padding(.horizontal, 7)
        .padding(.bottom, 25)
        .frame(width: 181, alignment: .topLeading)
        .background(DS.white)
        .contentShape(Rectangle())
    }
}

/// Reproduit `res/layout/icon5.xml` (CustomAdapter3) : largeur 165, padding 7/7/bas 30,
/// image 100 coins 13, "fv  lv  ds" 9 black_70 en haut à droite, "fv  lv" 9 black_70 en bas à droite.
struct SearchCiteCardSmall: View {
    let cite: Cite

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Image(cite.imageName)
                .resizable()
                .scaledToFill()
                .frame(width: 151, height: 100)
                .clipShape(RoundedRectangle(cornerRadius: 13))
            HStack(alignment: .top, spacing: 0) {
                SearchCiteInfoColumn(cite: cite)
                Spacer(minLength: 0)
                VStack(alignment: .trailing, spacing: 0) {
                    Text(cite.district)
                        .font(.system(size: 12))
                        .foregroundStyle(DS.blackA(70))
                        .padding(.top, 4)
                    Spacer(minLength: 0)
                    Text("\(cite.pricePerMonth.formatted()) F")
                        .font(.system(size: 12))
                        .foregroundStyle(DS.blackA(70))
                }
            }
            .fixedSize(horizontal: false, vertical: true)
        }
        .padding(.horizontal, 7)
        .padding(.bottom, 30)
        .frame(width: 165, alignment: .topLeading)
        .background(DS.white)
    }
}

/// Colonne gauche commune aux deux cartes : nom, "24 chambre", "20 chambre libre",
/// "4 en cours de l'iberation" (textes de fragment_search.kt).
struct SearchCiteInfoColumn: View {
    let cite: Cite

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(cite.name)
                .font(.system(size: 13, weight: .bold))
                .foregroundStyle(DS.black)
                .lineLimit(1)
                .padding(.top, 4)
            Text("\(cite.totalRooms) chambre")
                .font(.system(size: 12))
                .foregroundStyle(DS.blackA(70))
                .padding(.top, 4)
            Text("\(cite.freeRooms) chambre libre")
                .font(.system(size: 12))
                .foregroundStyle(DS.blackA(70))
            Text("\(cite.releasingRooms) en cours de libération")
                .font(.system(size: 12))
                .foregroundStyle(DS.blackA(70))
        }
    }
}
