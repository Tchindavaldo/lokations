import SwiftUI

/// Accueil (équivalent HomeFragment + fragment_ligne1..7).
struct HomeView: View {
    private let sections = MockData.homeSections

    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: DS.Space.lg) {
                    HomeCarousel(images: ["m88", "m3", "m91", "m6"])
                    HomeCategoriesRow()
                    ForEach(sections) { section in
                        HomeSectionRow(section: section)
                    }
                }
                .padding(.vertical, DS.Space.md)
            }
            .navigationTitle("Lokations")
            .navigationDestination(for: Cite.self) { CiteDetailView(cite: $0) }
        }
    }
}

/// Liste complète d'une section ("tout voir").
struct HomeCiteListView: View {
    let section: CiteSection

    var body: some View {
        List(section.cites) { cite in
            NavigationLink(value: cite) {
                HStack(spacing: DS.Space.md) {
                    Image(cite.imageName).resizable().scaledToFill()
                        .frame(width: 80, height: 60)
                        .clipShape(RoundedRectangle(cornerRadius: DS.Radius.sm))
                    VStack(alignment: .leading, spacing: DS.Space.xs) {
                        Text(cite.name).font(.headline)
                        Text("\(cite.city) - \(cite.pricePerMonth.formatted()) FCFA / mois")
                            .font(.caption)
                            .foregroundStyle(DS.textSecondary)
                    }
                }
            }
        }
        .listStyle(.plain)
        .navigationTitle(section.title.capitalized)
    }
}
