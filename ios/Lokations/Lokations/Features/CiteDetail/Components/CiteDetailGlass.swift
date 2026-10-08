import SwiftUI

// Maquette « B · Photo immersive — Détail » : éléments en verre (Liquid Glass sur
// iOS 26, repli blanc 22 % + .ultraThinMaterial sur iOS 17).

struct CiteDetailGlass<S: Shape>: ViewModifier {
    let shape: S

    func body(content: Content) -> some View {
        #if compiler(>=6.2)
        if #available(iOS 26, *) {
            content.glassEffect(.regular, in: shape)
        } else {
            fallback(content)
        }
        #else
        fallback(content)
        #endif
    }

    private func fallback(_ content: Content) -> some View {
        content
            .background(DS.whiteA(22), in: shape)
            .background(.ultraThinMaterial, in: shape)
    }
}

extension View {
    func citeDetailGlass<S: Shape>(_ shape: S) -> some View {
        modifier(CiteDetailGlass(shape: shape))
    }
}

/// Bouton rond 44 pt (retour, favori).
struct CiteDetailCircleButton: View {
    let systemName: String
    var tint: Color = DS.white
    let label: String
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            Image(systemName: systemName)
                .font(.system(size: 18, weight: .semibold))
                .foregroundStyle(tint)
                .frame(width: 44, height: 44)
                .citeDetailGlass(Circle())
        }
        .buttonStyle(.plain)
        .accessibilityLabel(label)
    }
}

/// Puce 36 pt (Photos, Avis, Carte, Appeler).
struct CiteDetailChipLabel: View {
    let systemName: String
    let title: String

    var body: some View {
        HStack(spacing: 6) {
            Image(systemName: systemName).font(.system(size: 13, weight: .semibold))
            Text(title).font(.system(size: 14, weight: .semibold)).lineLimit(1)
        }
        .foregroundStyle(DS.white)
        .padding(.horizontal, 12)
        .frame(height: 36)
        .citeDetailGlass(Capsule())
    }
}

/// Visionneuse plein écran de la galerie.
struct CiteDetailPhotoViewer: View {
    let images: [String]
    @State var index: Int
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        ZStack(alignment: .topTrailing) {
            DS.black.ignoresSafeArea()
            TabView(selection: $index) {
                ForEach(images.indices, id: \.self) { i in
                    Image(images[i]).resizable().scaledToFit().tag(i)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .always))
            .ignoresSafeArea()
            CiteDetailCircleButton(systemName: "xmark", label: "Fermer") { dismiss() }
                .padding(16)
        }
    }
}
