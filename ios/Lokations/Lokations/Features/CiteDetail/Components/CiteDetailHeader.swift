import SwiftUI

// Reproduit l'en-tête commun de fragment_detail.xml et fragment_comment.xml :
// CardView 55x55 (r55, fond #80FFFFFF) avec m6, trois textes, puis une pastille
// alignée en bas (round_black_10_10, 14sp bold black_70, padding 10/8).

struct CiteDetailHeader: View {
    let chip: String

    var body: some View {
        HStack(alignment: .bottom, spacing: 0) {
            Image("m6")
                .resizable()
                .scaledToFill()
                .frame(width: 55, height: 55)
                .background(DS.whiteA(50))
                .clipShape(Circle())

            VStack(alignment: .leading, spacing: 0) {
                Text("Cité Hypocrate")
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                Spacer(minLength: 0)
                Text("Baganté")
                    .font(.system(size: 10))
                    .foregroundStyle(DS.blackA(50))
                Text("Chambre10,  260 000/Ans")
                    .font(.system(size: 10))
                    .foregroundStyle(DS.blackA(50))
                    .padding(.bottom, 5)
            }
            .padding(.leading, 15)
            .padding(.bottom, 2)
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)

            CiteDetailChip(text: chip, size: 14, color: DS.blackA(70), bold: true,
                           background: DS.blackA(10), radius: 10, h: 10, v: 8)
        }
        .frame(height: 55)
        .padding(.leading, 15)
        .padding(.trailing, 25)
    }
}

/// TextView sur fond arrondi (drawables round_*).
struct CiteDetailChip: View {
    let text: String
    let size: CGFloat
    let color: Color
    var bold = false
    let background: Color
    let radius: CGFloat
    let h: CGFloat
    let v: CGFloat

    var body: some View {
        Text(text)
            .font(.system(size: size, weight: bold ? .bold : .regular))
            .foregroundStyle(color)
            .padding(.horizontal, h)
            .padding(.vertical, v)
            .background(background, in: RoundedRectangle(cornerRadius: radius))
    }
}
