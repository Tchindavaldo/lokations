import SwiftUI

// Liste "tout voir" / catégorie / recherche de l'accueil : lignes de res/layout/itemligne2.xml (ligne3).
struct HomeCiteListView: View {
    let title: String
    let cites: [Cite]

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 25) {
                if cites.isEmpty {
                    HomeText("Aucune cité trouvée", 14, DS.blackA(50))
                        .frame(maxWidth: .infinity)
                        .padding(.top, 40)
                }
                ForEach(cites) { cite in
                    NavigationLink(value: cite) { HomeLigne3Row(cite: cite) }
                        .buttonStyle(.plain)
                }
            }
            .padding(14)
        }
        .background(DS.android(0xF9F2FC))
        .navigationTitle(title)
        .navigationBarTitleDisplayMode(.inline)
    }
}
