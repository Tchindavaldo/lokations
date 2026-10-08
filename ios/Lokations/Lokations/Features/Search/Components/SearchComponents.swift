import SwiftUI

/// Onglets de critère (équivalent TabLayout prix / ville / ...).
struct SearchCriterionBar: View {
    @Binding var selection: SearchCriterion
    @Namespace private var underline

    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: DS.Space.lg) {
                ForEach(SearchCriterion.allCases) { criterion in
                    Button {
                        withAnimation(.snappy) { selection = criterion }
                    } label: {
                        VStack(spacing: DS.Space.xs) {
                            Text(criterion.rawValue.uppercased())
                                .font(.subheadline.weight(.medium))
                                .foregroundStyle(selection == criterion ? DS.textPrimary : DS.textSecondary)
                            if selection == criterion {
                                Capsule().fill(DS.ink).frame(height: 3)
                                    .matchedGeometryEffect(id: "underline", in: underline)
                            } else {
                                Capsule().fill(.clear).frame(height: 3)
                            }
                        }
                        .fixedSize()
                    }
                }
            }
            .padding(.horizontal, DS.Space.md)
        }
    }
}

/// Copie dédiée à la recherche (R16) : carte pleine largeur.
struct SearchResultRow: View {
    let cite: Cite

    var body: some View {
        VStack(alignment: .leading, spacing: DS.Space.sm) {
            Image(cite.imageName).resizable().scaledToFill()
                .frame(height: 180)
                .frame(maxWidth: .infinity)
                .clipShape(RoundedRectangle(cornerRadius: DS.Radius.md))
            HStack(alignment: .firstTextBaseline) {
                VStack(alignment: .leading, spacing: DS.Space.xs) {
                    Text(cite.name).font(.headline)
                    Label("\(cite.district), \(cite.city)", systemImage: "mappin")
                        .font(.caption)
                        .foregroundStyle(DS.textSecondary)
                }
                Spacer()
                VStack(alignment: .trailing, spacing: DS.Space.xs) {
                    Text("\(cite.pricePerMonth.formatted()) F").font(.headline)
                    Text("\(cite.freeRooms) libres").font(.caption).foregroundStyle(DS.success)
                }
            }
        }
        .foregroundStyle(DS.textPrimary)
    }
}

struct ContentUnavailableCompat: View {
    let query: String

    var body: some View {
        VStack(spacing: DS.Space.sm) {
            Image(systemName: "magnifyingglass").font(.largeTitle)
            Text("Aucun résultat pour \"\(query)\"").font(.headline)
        }
        .foregroundStyle(DS.textSecondary)
        .frame(maxWidth: .infinity)
        .padding(.top, 80)
    }
}
