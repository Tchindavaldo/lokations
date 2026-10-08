import SwiftUI

// Reproduit le ViewPager de res/layout/fragment_photo.xml avec
// inflate_view_pager_image_fragment_photo.xml (ImageView centerCrop plein cadre)
// et le PageTransformer de fragment_photo.kt :
// alpha = 1 - |p|, échelle = 0.35 + 0.65 * (1 - |p|), pageMargin 20px.

struct CitePhotoPager: View {
    let images: [String]

    @State private var index = 0
    @State private var drag: CGFloat = 0

    var body: some View {
        GeometryReader { geo in
            let w = geo.size.width
            let h = geo.size.height
            let step = w + 20.0 / 3
            ZStack {
                ForEach(images.indices, id: \.self) { i in
                    let pos = CGFloat(i - index) + drag / step
                    let absPos = abs(pos)
                    Image(images[i])
                        .resizable()
                        .scaledToFill()
                        .frame(width: w, height: h)
                        .clipped()
                        .scaleEffect(absPos > 1 ? 1 : 0.35 + 0.65 * (1 - absPos))
                        .opacity(absPos > 1 ? 0 : 1 - absPos)
                        .offset(x: pos * step)
                }
            }
            .frame(width: w, height: h)
            .clipped()
            .contentShape(Rectangle())
            .gesture(
                DragGesture()
                    .onChanged { drag = $0.translation.width }
                    .onEnded { value in
                        var next = index
                        if value.predictedEndTranslation.width < -w / 2 { next += 1 }
                        if value.predictedEndTranslation.width > w / 2 { next -= 1 }
                        withAnimation(.easeOut(duration: 0.3)) {
                            index = min(max(next, 0), images.count - 1)
                            drag = 0
                        }
                    }
            )
        }
        .background(DS.android(0x000000)) // CardView cardBackgroundColor #000000
    }
}
