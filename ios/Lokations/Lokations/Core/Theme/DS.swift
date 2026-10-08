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

    // MARK: Palette Android (res/values/colors.xml) — valeurs exactes
    static let white = Color(hex: 0xFFFFFF)
    static let black = Color(hex: 0x000000)
    static let purple200 = Color(hex: 0xBB86FC)
    static let purple500 = Color(hex: 0x6200EE)
    static let purple700 = Color(hex: 0x3700B3)
    static let teal200 = Color(hex: 0x03DAC5)
    static let teal700 = Color(hex: 0x018786)
    /// white_10 … white_70, black_5 … black_90 : même canal alpha qu'Android.
    static func whiteA(_ percent: Double) -> Color { white.opacity(percent / 100) }
    static func blackA(_ percent: Double) -> Color { black.opacity(percent / 100) }
    /// Couleur littérale d'un layout XML Android (#RRGGBB ou #AARRGGBB).
    static func android(_ argb: UInt32, hasAlpha: Bool = false) -> Color {
        hasAlpha ? Color(hex: argb & 0xFFFFFF).opacity(Double(argb >> 24) / 255) : Color(hex: argb)
    }

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

extension Color {
    /// 0xRRGGBB, réservé à DS (R12).
    init(hex: UInt32) {
        self.init(red: Double((hex >> 16) & 0xFF) / 255,
                  green: Double((hex >> 8) & 0xFF) / 255,
                  blue: Double(hex & 0xFF) / 255)
    }
}
