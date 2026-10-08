import SwiftUI

// Reproduit : res/layout/fragment_boutique_historique_transcastion.xml, blocs
// item1_item2_page_boutique / item5 (icone @drawable/photo 24dp, libelles 12sp, points 8dp).
struct BoutiqueTransactionLine: Identifiable {
    let id = UUID()
    let label: String
    var labelColor: Color = DS.blackA(50)
    let value: String
    var valueColor: Color = DS.black
    var valueSize: CGFloat = 14
    var valueBold = true
    var topPadding: CGFloat = 12
}

struct BoutiqueTransactionBlock: View {
    let title: String
    let lines: [BoutiqueTransactionLine]

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .center, spacing: 0) {
                Image("photo")
                    .resizable()
                    .scaledToFill()
                    .frame(width: 24, height: 24)
                HStack(alignment: .bottom, spacing: 0) {
                    Text("Historique")
                        .font(.system(size: 12))
                        .foregroundStyle(DS.black)
                        .padding(.leading, 5)
                    Spacer(minLength: 0)
                    Text(title)
                        .font(.system(size: 16, weight: .bold))
                        .foregroundStyle(DS.black)
                }
            }
            .padding(.leading, 15)
            .padding(.trailing, 15)

            ForEach(lines) { line in
                HStack(alignment: .bottom, spacing: 0) {
                    Text(line.label)
                        .font(.system(size: 12))
                        .foregroundStyle(line.labelColor)
                    Spacer(minLength: 0)
                    Text(line.value)
                        .font(.system(size: line.valueSize, weight: line.valueBold ? .bold : .regular))
                        .foregroundStyle(line.valueColor)
                }
                .padding(.top, line.topPadding)
                .padding(.leading, 20)
                .padding(.trailing, 15)
            }

            HStack(spacing: 5) {
                Circle().fill(DS.black).frame(width: 8, height: 8)
                ForEach(0..<3, id: \.self) { _ in
                    Circle().fill(DS.white).frame(width: 8, height: 8)
                }
            }
            .padding(.top, 12)
            .padding(.leading, 20)
        }
    }
}
