import SwiftUI

/// Reproduit `res/layout/icon3.xml` (CustomAdapter) : fond blanc, paddingTop 10,
/// en-tête (13sp / 10sp agrandi à 12) en black_50, liste horizontale `icon4.xml` puis
/// liste `icon5.xml`, sur fond #08000000. Chaque carte ouvre sa propre cité.
struct SearchSectionBlock: View {
    let title: String
    let topCites: [Cite]
    let bottomTitle: String
    let bottomCites: [Cite]

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            SearchSectionHeader(title: title, trailing: "\(topCites.count) cités")
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(alignment: .top, spacing: 0) {
                    ForEach(topCites) { cite in
                        NavigationLink(value: cite) { SearchCiteCardLarge(cite: cite) }
                            .buttonStyle(.plain)
                    }
                }
            }
            .background(DS.android(0x08000000, hasAlpha: true))

            if !bottomCites.isEmpty {
                SearchSectionHeader(title: bottomTitle, trailing: "\(bottomCites.count) cités")
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(alignment: .top, spacing: 0) {
                        ForEach(bottomCites) { cite in
                            NavigationLink(value: cite) { SearchCiteCardSmall(cite: cite) }
                                .buttonStyle(.plain)
                        }
                    }
                }
                .background(DS.android(0x08000000, hasAlpha: true))
            }
        }
        .padding(.top, 10)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(DS.white)
    }
}

/// Ligne d'en-tête de `icon3.xml` (text1Linge / text2Linge).
struct SearchSectionHeader: View {
    let title: String
    let trailing: String

    var body: some View {
        HStack(alignment: .top, spacing: 0) {
            Text(title)
                .font(.system(size: 13))
                .foregroundStyle(DS.blackA(50))
            Spacer(minLength: 0)
            Text(trailing)
                .font(.system(size: 12))
                .foregroundStyle(DS.blackA(50))
        }
        .padding(.horizontal, 7)
    }
}
