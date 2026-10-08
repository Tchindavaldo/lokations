import SwiftUI

// Reproduit : res/layout/fragment_boutique.xml, CardView @id/entete
// (infos_entete_middle 39 %, infos_entete_botom 25 % x 70 %, TabLayout 20dp, style @style/size).
struct BoutiqueHeaderCard: View {
    let width: CGFloat
    let height: CGFloat
    let titles: [String]
    @Binding var selection: Int

    /// Hauteur interieure (paddingTop 20dp retire).
    private var content: CGFloat { max(height - 20, 0) }

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Spacer(minLength: 0)
            middle.frame(height: content * 0.39)
            Spacer(minLength: 0)
            bottom
                .frame(width: width * 0.7, height: content * 0.25)
                .padding(.leading, 25)
                .padding(.bottom, 10)
            Spacer(minLength: 0)
            BoutiqueTabStrip(titles: titles, selection: $selection)
                .frame(height: 20)
                .padding(.horizontal, 15)
                .padding(.bottom, 10)
        }
        .padding(.top, 20)
        .frame(width: width, height: height)
        .background(alignment: .bottom) {
            UnevenRoundedRectangle(bottomLeadingRadius: 35, bottomTrailingRadius: 35)
                .fill(DS.android(0xE6E0E3))
                .ignoresSafeArea(edges: .top)
        }
    }

    private var middle: some View {
        HStack(spacing: 0) {
            VStack(alignment: .leading, spacing: 0) {
                Text("Montant Du Solde")
                    .font(.system(size: 15))
                    .foregroundStyle(DS.blackA(50))
                Text("10 340 000f")
                    .font(.system(size: 30, weight: .bold))
                    .foregroundStyle(DS.black)
            }
            .padding(.leading, 25)
            Spacer(minLength: 0)
            Image("ic_baseline_shopping_bag_24")
                .resizable()
                .frame(width: 20, height: 20)
                .padding(.trailing, 15)
        }
    }

    private var bottom: some View {
        HStack(alignment: .center, spacing: 0) {
            revenue("440 000f")
            Spacer(minLength: 0)
            Rectangle()
                .fill(DS.black)
                .frame(width: 0.8, height: 34 * 0.6)
            Spacer(minLength: 0)
            revenue("4 400 000f")
        }
    }

    private func revenue(_ value: String) -> some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Revenu Mensuel")
                .font(.system(size: 10))
                .foregroundStyle(DS.blackA(50))
            Text(value)
                .font(.system(size: 14, weight: .bold))
                .foregroundStyle(DS.black)
                .padding(.top, 10)
        }
    }
}

/// TabLayout scrollable centre, texte 9sp majuscules (TextAppearance.Design.Tab),
/// indicateur noir largeur du texte (tabIndicatorFullWidth=false).
struct BoutiqueTabStrip: View {
    let titles: [String]
    @Binding var selection: Int

    var body: some View {
        GeometryReader { geo in
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 0) {
                    ForEach(titles.indices, id: \.self) { index in
                        tab(index)
                    }
                }
                .frame(minWidth: geo.size.width, minHeight: geo.size.height)
            }
        }
    }

    private func tab(_ index: Int) -> some View {
        let label = Text(titles[index].uppercased())
            .font(.system(size: 9, weight: .medium))
        return Button {
            withAnimation { selection = index }
        } label: {
            label
                .foregroundStyle(DS.black)
                .lineLimit(1)
                .padding(.horizontal, 12)
                .frame(minWidth: 72, maxHeight: .infinity)
                .overlay(alignment: .bottom) {
                    if selection == index {
                        label.lineLimit(1).hidden()
                            .frame(height: 2)
                            .background(DS.black)
                    }
                }
                .contentShape(Rectangle())
        }
        .buttonStyle(.plain)
    }
}
