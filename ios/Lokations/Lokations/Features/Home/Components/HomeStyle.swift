import SwiftUI

// Petits éléments communs aux layouts Home (itemligne2.xml, view_pager_img_slide.xml) :
// TextView (taille sp, couleur, gras), badge "6 /10", points de pagination, fondu d'apparition.

struct HomeText: View {
    let text: String
    let size: CGFloat
    let color: Color
    var bold = false

    init(_ text: String, _ size: CGFloat, _ color: Color, bold: Bool = false) {
        self.text = text
        self.size = size
        self.color = color
        self.bold = bold
    }

    var body: some View {
        Text(text)
            .font(.system(size: size, weight: bold ? .bold : .regular))
            .foregroundStyle(color)
            .lineLimit(1)
    }
}

/// Badge "6 /10" (round_black_50_30 ou round_black_50).
struct HomeScoreBadge: View {
    let fill: Color
    let trailing: CGFloat

    var body: some View {
        HStack(spacing: 0) {
            HomeText("6", 8, DS.android(0xFFE082))
            HomeText(" /10", 8, DS.white, bold: true)
        }
        .padding(.leading, 4).padding(.top, 1).padding(.trailing, trailing).padding(.bottom, 1)
        .background(Capsule().fill(fill))
    }
}

/// 4 points 5dp (round_black_50_50, round_black_50, round_black_50_50, round_black_50_50).
struct HomeSlideDots: View {
    var body: some View {
        HStack(spacing: 0) {
            ForEach(0..<4, id: \.self) { i in
                Circle().fill(i == 1 ? DS.black : DS.blackA(50))
                    .frame(width: 5, height: 5)
                    .padding(.leading, 5)
            }
        }
    }
}

/// Apparition en fondu des lignes (animate().alpha(1f) de l'adaptateur).
struct HomeFadeIn: ViewModifier {
    let duration: Double
    @State private var shown = false

    func body(content: Content) -> some View {
        content
            .opacity(shown ? 1 : 0)
            .onAppear { withAnimation(.easeIn(duration: duration)) { shown = true } }
    }
}
