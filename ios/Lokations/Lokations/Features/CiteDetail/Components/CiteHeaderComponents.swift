import SwiftUI

struct CiteGallery: View {
    let images: [String]

    var body: some View {
        TabView {
            ForEach(images, id: \.self) { name in
                Image(name).resizable().scaledToFill()
            }
        }
        .tabViewStyle(.page(indexDisplayMode: .always))
        .frame(height: 280)
        .clipped()
    }
}

struct CiteHeader: View {
    let cite: Cite

    var body: some View {
        VStack(alignment: .leading, spacing: DS.Space.xs) {
            Text(cite.name).font(.title.bold())
            Label("\(cite.district), \(cite.city)", systemImage: "mappin.and.ellipse")
                .foregroundStyle(DS.textSecondary)
            Text("\(cite.pricePerMonth.formatted()) FCFA / mois")
                .font(.title3.bold())
                .foregroundStyle(DS.accent)
        }
    }
}

struct CiteInfoTab: View {
    let cite: Cite

    var body: some View {
        VStack(alignment: .leading, spacing: DS.Space.md) {
            Text(cite.summary)
            Grid(alignment: .leading, horizontalSpacing: DS.Space.md, verticalSpacing: DS.Space.sm) {
                GridRow { Image(systemName: "bed.double"); Text("\(cite.totalRooms) chambres") }
                GridRow { Image(systemName: "checkmark.circle"); Text("\(cite.freeRooms) chambres libres") }
                GridRow { Image(systemName: "clock"); Text("\(cite.releasingRooms) en cours de libération") }
            }
        }
    }
}

struct CiteRoomsTab: View {
    let cite: Cite

    var body: some View {
        LazyVGrid(columns: [GridItem(.adaptive(minimum: 150), spacing: DS.Space.sm)], spacing: DS.Space.sm) {
            ForEach(1...min(cite.totalRooms, 12), id: \.self) { number in
                let isFree = number % 3 != 0
                HStack {
                    Image(systemName: "door.left.hand.closed")
                    Text("Chambre \(number)")
                    Spacer()
                    Circle()
                        .fill(isFree ? DS.success : DS.danger)
                        .frame(width: 10, height: 10)
                        .accessibilityLabel(isFree ? "libre" : "occupée")
                }
                .padding(DS.Space.md)
                .background(DS.surface, in: RoundedRectangle(cornerRadius: DS.Radius.sm))
            }
        }
    }
}
