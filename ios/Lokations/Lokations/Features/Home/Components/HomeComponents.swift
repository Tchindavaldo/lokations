import SwiftUI

/// Carrousel automatique (équivalent adapteur_image_slide_1_home_page).
struct HomeCarousel: View {
    let images: [String]
    @State private var index = 0

    var body: some View {
        TabView(selection: $index) {
            ForEach(images.indices, id: \.self) { i in
                Image(images[i]).resizable().scaledToFill().tag(i)
            }
        }
        .tabViewStyle(.page(indexDisplayMode: .always))
        .frame(height: 220)
        .clipShape(RoundedRectangle(cornerRadius: DS.Radius.lg))
        .padding(.horizontal, DS.Space.md)
        .task {
            while !Task.isCancelled {
                try? await Task.sleep(for: .seconds(4))
                withAnimation { index = (index + 1) % max(images.count, 1) }
            }
        }
    }
}

struct HomeCategoriesRow: View {
    private let categories: [(name: String, icon: String)] = [
        ("Chambres", "bed.double.fill"), ("Studios", "building.fill"),
        ("Appartements", "building.2.fill"), ("Cités", "house.lodge.fill"), ("Villas", "house.fill"),
    ]

    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: DS.Space.md) {
                ForEach(categories, id: \.name) { category in
                    VStack(spacing: DS.Space.xs) {
                        Image(systemName: category.icon)
                            .font(.title2)
                            .foregroundStyle(DS.ink)
                            .frame(width: 60, height: 60)
                            .background(DS.surface, in: Circle())
                        Text(category.name).font(.caption)
                    }
                }
            }
            .padding(.horizontal, DS.Space.md)
        }
    }
}

struct HomeSectionRow: View {
    let section: CiteSection

    var body: some View {
        VStack(alignment: .leading, spacing: DS.Space.sm) {
            HStack {
                Text(section.title).font(.title3).foregroundStyle(DS.textSecondary)
                Spacer()
                NavigationLink("tout voir") { HomeCiteListView(section: section) }
                    .font(.subheadline)
                    .foregroundStyle(DS.textSecondary)
            }
            .padding(.horizontal, DS.Space.md)

            ScrollView(.horizontal, showsIndicators: false) {
                LazyHStack(spacing: DS.Space.md) {
                    ForEach(section.cites) { cite in
                        NavigationLink(value: cite) { HomeCiteCard(cite: cite) }
                            .buttonStyle(.plain)
                    }
                }
                .padding(.horizontal, DS.Space.md)
            }
        }
    }
}

struct HomeCiteCard: View {
    let cite: Cite

    var body: some View {
        VStack(alignment: .leading, spacing: DS.Space.xs) {
            Image(cite.imageName).resizable().scaledToFill()
                .frame(width: 240, height: 160)
                .clipShape(RoundedRectangle(cornerRadius: DS.Radius.md))
                .shadow(color: DS.scrim(0.2), radius: 4, y: 2)
            Text(cite.name).font(.headline).lineLimit(1)
            Text("\(cite.totalRooms) chambres").font(.caption)
            Text("\(cite.freeRooms) chambres libres").font(.caption)
            HStack {
                Text("\(cite.releasingRooms) en cours de libération").font(.caption)
                Spacer()
                Text("\(cite.pricePerMonth.formatted()) F").font(.caption.bold())
            }
        }
        .foregroundStyle(DS.textPrimary)
        .frame(width: 240)
    }
}
