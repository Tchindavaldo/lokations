import SwiftUI

// Reproduit : res/layout/view_pager_img_slide.xml (Adapteur_image_home_slide + Adapteur_infini) :
// ViewPager infini, transformateur échelle 0.70 + alpha, défilement auto (1500 ms, 7000 ms, 1500 ms).

struct HomeSlide: Identifiable {
    let id = UUID()
    let image: String
    let titre: String
    let description: String

    /// itemList de HomeFragment .kt
    static let all = [
        HomeSlide(image: "m7", titre: "cité Rose",
                  description: "3 chambre en cour de l'iberartion, 3 chambre disponible"),
    ]
}

struct HomeSlidePager: View {
    let slides: [HomeSlide]
    var autoScroll = false

    private static let copies = 200
    @State private var position: Int? = HomeSlidePager.copies / 2

    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(spacing: 0) {
                ForEach(0..<(slides.count * Self.copies), id: \.self) { i in
                    HomeSlidePage(slide: slides[i % slides.count])
                        .containerRelativeFrame(.horizontal)
                        .scrollTransition(axis: .horizontal) { content, phase in
                            content
                                .scaleEffect(0.7 + 0.3 * (1 - abs(phase.value)))
                                .opacity(1 - abs(phase.value))
                        }
                }
            }
            .scrollTargetLayout()
        }
        .scrollTargetBehavior(.paging)
        .scrollPosition(id: $position)
        .task(id: autoScroll) {
            guard autoScroll else { return }
            try? await Task.sleep(for: .milliseconds(1500))
            while !Task.isCancelled {
                withAnimation(.easeInOut(duration: 1.5)) {
                    position = (position ?? Self.copies / 2) + 1
                }
                try? await Task.sleep(for: .seconds(7))
            }
        }
    }
}

struct HomeSlidePage: View {
    let slide: HomeSlide

    var body: some View {
        ZStack(alignment: .bottom) {
            Color.clear.overlay(Image(slide.image).resizable().scaledToFill())
            HStack(spacing: 0) {
                Image(slide.image).resizable().scaledToFill()
                    .frame(width: 35, height: 35)
                    .clipShape(RoundedRectangle(cornerRadius: 25))
                    .padding(.leading, 20).padding(.top, 5)
                VStack(alignment: .leading, spacing: 0) {
                    HomeText(slide.titre, 15, DS.whiteA(90), bold: true)
                    HomeText(slide.description, 10, DS.whiteA(70))
                }
                .padding(.leading, 15)
                Spacer(minLength: 0)
            }
            .padding(.bottom, 1)
            .background(DS.blackA(70))
        }
        .clipShape(RoundedRectangle(cornerRadius: 15))
    }
}
