import SwiftUI

/// Reproduit `res/layout/icon3.xml` (CustomAdapter) : fond blanc, paddingTop 10,
/// en-tête "top qualité" (13sp) / "tout voir" (10sp) en black_50, rv2 (`icon4.xml`, cliquable)
/// puis même en-tête et rv3 (`icon5.xml`), listes horizontales sur fond #08000000.
struct SearchSectionBlock: View {
    let topCites: [Cite]
    let bottomCites: [Cite]

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            SearchSectionHeader()
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(alignment: .top, spacing: 0) {
                    ForEach(Array(topCites.enumerated()), id: \.offset) { _, cite in
                        NavigationLink(value: cite) { SearchCiteCardLarge(cite: cite) }
                            .buttonStyle(.plain)
                    }
                }
            }
            .background(DS.android(0x08000000, hasAlpha: true))

            SearchSectionHeader()
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(alignment: .top, spacing: 0) {
                    ForEach(Array(bottomCites.enumerated()), id: \.offset) { _, cite in
                        SearchCiteCardSmall(cite: cite)
                    }
                }
            }
            .background(DS.android(0x08000000, hasAlpha: true))
        }
        .padding(.top, 10)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(DS.white)
    }
}

/// Ligne d'en-tête de `icon3.xml` (text1Linge / text2Linge).
struct SearchSectionHeader: View {
    var body: some View {
        HStack(alignment: .top, spacing: 0) {
            Text("top qualité")
                .font(.system(size: 13))
                .foregroundStyle(DS.blackA(50))
            Spacer(minLength: 0)
            Text("tout voir")
                .font(.system(size: 10))
                .foregroundStyle(DS.blackA(50))
        }
    }
}
