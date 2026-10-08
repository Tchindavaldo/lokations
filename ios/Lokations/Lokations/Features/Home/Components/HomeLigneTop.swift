import SwiftUI

// Reproduit : res/layout/itemligne2.xml — ligne2 ("Meilleur notes", 3 cartes 150x110)
// et ligne3 ("Nouvotés", 3 lignes image 80x75). Données : CustomHomeAdapterItemLigne2.kt.

struct HomeLigne2Section: View {
    let item: HomeLigne2Item
    let cite: (Int) -> Cite

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HomeLigneHeader(title: "Meilleur notes")
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 15) {
                    NavigationLink(value: cite(0)) {
                        HomeLigne2Card(image: item.image, bgRadius: 15, categorie: item.categorie,
                                       itemCategorie: item.itemCategorie, prix: item.prix,
                                       statut: item.text, ville: item.lieux)
                    }
                    NavigationLink(value: cite(1)) {
                        HomeLigne2Card(image: "m2", bgRadius: 15)
                    }
                    NavigationLink(value: cite(2)) {
                        HomeLigne2Card(image: "m3", bgRadius: 10)
                    }
                }
                .buttonStyle(.plain)
            }
        }
        .padding(.bottom, 35)
    }
}

struct HomeLigne2Card: View {
    let image: String
    let bgRadius: CGFloat
    var categorie = "cité Mandela"
    var itemCategorie = "chambre1"
    var prix = "300 000/Ans"
    var statut = "actuellement disponible"
    var ville = "bastos"

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            ZStack(alignment: .topTrailing) {
                RoundedRectangle(cornerRadius: bgRadius).fill(DS.blackA(5))
                Color.clear
                    .overlay(Image(image).resizable().scaledToFill())
                    .clipShape(RoundedRectangle(cornerRadius: 15))
                HomeScoreBadge(fill: DS.blackA(50), trailing: 3)
                    .padding(.top, 3).padding(.trailing, 5)
            }
            .frame(width: 150, height: 110)
            HStack(spacing: 0) {
                HomeText(categorie, 10, DS.black, bold: true)
                HomeText(", ", 10, DS.black, bold: true)
                HomeText(itemCategorie, 10, DS.black, bold: true)
            }
            .padding(.top, 3)
            HStack(alignment: .bottom, spacing: 0) {
                VStack(alignment: .leading, spacing: 0) {
                    HomeText(prix, 7, DS.blackA(70))
                    HomeText(statut, 8, DS.blackA(70))
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                Image("plce").resizable().scaledToFit().frame(width: 12, height: 12)
                HomeText(ville, 8, DS.black)
            }
        }
        .frame(width: 150)
    }
}

struct HomeLigne3Section: View {
    let cite: (Int) -> Cite
    private let images = ["m88", "m4", "m5"]

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HomeLigneHeader(title: "Nouvotés")
            ForEach(images.indices, id: \.self) { i in
                NavigationLink(value: cite(i)) { HomeLigne3Row(image: images[i]) }
                    .buttonStyle(.plain)
                    .padding(.bottom, i < images.count - 1 ? 25 : 0)
            }
        }
        .padding(.bottom, 35)
    }
}

struct HomeLigne3Row: View {
    let image: String

    var body: some View {
        HStack(alignment: .top, spacing: 0) {
            Image(image).resizable().scaledToFill()
                .frame(width: 80, height: 75)
                .clipShape(RoundedRectangle(cornerRadius: 10))
                .background(RoundedRectangle(cornerRadius: 10).fill(DS.blackA(5)))
            VStack(alignment: .leading, spacing: 0) {
                HStack(spacing: 0) {
                    HomeText("cité Mandela", 10, DS.black, bold: true)
                    HomeText(", ", 10, DS.black, bold: true)
                    HomeText("yaoundé", 10, DS.black, bold: true)
                }
                .padding(.leading, 5).padding(.top, 3)
                HStack(spacing: 0) {
                    HStack(spacing: 0) {
                        HomeText("Note", 10, DS.blackA(70)).padding(.leading, 5)
                        HomeText("60", 11, DS.android(0xFFE082)).padding(.leading, 3)
                        HomeText("/100 ", 10, DS.blackA(50))
                        HomeText("Qualite", 10, DS.blackA(70)).padding(.leading, 5)
                        Image("fv").resizable().scaledToFit().frame(width: 6, height: 10).padding(.leading, 3)
                        HomeText("4.5", 10, DS.black)
                        HomeText("Prix:", 10, DS.blackA(70)).padding(.leading, 8)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    HomeText("222 000", 10, DS.black, bold: true)
                    HomeText(" /", 10, DS.black, bold: true)
                    HomeText("Mois", 10, DS.black, bold: true)
                }
                .padding(.top, 5)
                HStack(alignment: .bottom, spacing: 0) {
                    HomeText("Fin de construction", 10, DS.blackA(70))
                        .padding(.leading, 5).padding(.bottom, 1)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    HomeText("03 - 04 - 2025", 10, DS.black, bold: true)
                }
                .padding(.top, 3)
                HStack(spacing: 0) {
                    HomeText("2", 10, DS.black).padding(.leading, 5)
                    HomeText(" chambres ", 10, DS.blackA(70))
                    HomeText("1", 10, DS.black).padding(.leading, 3)
                    HomeText("salon", 10, DS.blackA(70))
                    HomeText("1", 10, DS.black).padding(.leading, 4)
                    HomeText("Douche", 10, DS.blackA(70))
                    HomeText("1", 10, DS.black).padding(.leading, 3)
                    HomeText("Cuisine", 10, DS.blackA(70))
                }
                .padding(.top, 3)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .contentShape(Rectangle())
    }
}
