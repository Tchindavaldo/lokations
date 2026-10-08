import SwiftUI

/// Reproduit le TabLayout de `res/layout/fragment_search.xml` : mode scrollable,
/// texte 9sp (agrandi à 12) noir en majuscules (style `size`), indicateur noir 2dp à la largeur du texte.
struct SearchTabStrip: View {
    let titles: [String]
    @Binding var selection: Int

    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 0) {
                ForEach(Array(titles.enumerated()), id: \.offset) { index, title in
                    Button { selection = index } label: {
                        Text(title.uppercased())
                            .font(.system(size: 12, weight: .medium))
                            .foregroundStyle(DS.black)
                            .fixedSize()
                            .frame(maxHeight: .infinity)
                            .overlay(alignment: .bottom) {
                                if selection == index {
                                    Rectangle().fill(DS.black).frame(height: 2)
                                }
                            }
                            .padding(.horizontal, 12)
                            .frame(minWidth: 72)
                    }
                    .buttonStyle(.plain)
                }
            }
            .frame(height: 24)
        }
        .background(DS.android(0x00F9F2FC, hasAlpha: true))
    }
}
