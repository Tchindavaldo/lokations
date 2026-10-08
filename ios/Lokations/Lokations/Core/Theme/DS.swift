import SwiftUI

/// Design system Lokations. Toute couleur, rayon et espacement passe par ici (R18).
enum DS {
    // MARK: Couleurs de marque
    static let ink = Color(red: 0.141, green: 0.106, blue: 0.235)        // #241B3C
    static let accent = Color(red: 0.180, green: 0.490, blue: 0.196)     // vert des icônes Android
    static func accentAlpha(_ a: Double) -> Color { accent.opacity(a) }

    // MARK: Surfaces (adaptatives clair / sombre)
    static let background = Color(uiColor: .systemBackground)
    static let surface = Color(uiColor: .secondarySystemBackground)
    static let surfaceElevated = Color(uiColor: .tertiarySystemBackground)
    static let textPrimary = Color.primary
    static let textSecondary = Color.secondary
    static let onDark = Color.white
    static func onDarkAlpha(_ a: Double) -> Color { onDark.opacity(a) }
    static func scrim(_ a: Double) -> Color { Color.black.opacity(a) }

    // MARK: Statuts
    static let success = Color.green
    static let danger = Color.red
    static let favorite = Color.red

    // MARK: Rayons
    enum Radius {
        static let sm: CGFloat = 10
        static let md: CGFloat = 16
        static let lg: CGFloat = 20
    }

    // MARK: Espacements
    enum Space {
        static let xs: CGFloat = 4
        static let sm: CGFloat = 8
        static let md: CGFloat = 16
        static let lg: CGFloat = 24
    }
}
