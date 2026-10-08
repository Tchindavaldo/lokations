import SwiftUI

// Reproduit les deux menus de res/layout/frame_layout.xml
// (container_item_nav vertical + container_item_nav2 horizontal ; icônes du menu
// bottom_navigation_menu_detail_item : pps, photo, prix, comment, plce).
// Fond round_nav_detail (#33000000, r20) ; iOS 26 : `.glassEffect()` (R13).

/// Bouton LinearLayout padding 8 + ImageView 13x15 ; fond round_item_selected_nav_detail
/// (#E6E0E3, r25) ou round_item_nav_detail (transparent, r30), rayon borné à la demi-hauteur.
struct CiteDetailNavItem: View {
    let icon: String
    let selected: Bool
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            Image(icon)
                .resizable()
                .scaledToFill()
                .frame(width: 13, height: 15)
                .clipped()
                .padding(8)
                .background(selected ? DS.android(0xE6E0E3) : DS.android(0x00FFFFFF, hasAlpha: true),
                            in: Capsule())
        }
        .buttonStyle(.plain)
    }
}

/// Fond des barres : round_nav_detail, ou Liquid Glass sur iOS 26.
struct CiteDetailNavBackground: ViewModifier {
    func body(content: Content) -> some View {
        let shape = RoundedRectangle(cornerRadius: 20)
        #if compiler(>=6.2)
        if #available(iOS 26, *) {
            content.glassEffect(.regular, in: shape)
        } else {
            content.background(DS.blackA(20), in: shape)
        }
        #else
        content.background(DS.blackA(20), in: shape)
        #endif
    }
}

/// container_item_nav2 : hauteur 49dp, padding 20/10, items espacés de 15dp.
struct CiteDetailBottomBar: View {
    let selected: CiteDetailPage
    let onTap: (CiteDetailPage) -> Void

    var body: some View {
        HStack(spacing: 15) {
            item("pps2", .detail)
            item("photo2", .photo)
            item("prix2", .payment)
            item("comment2", .comment)
            item("plce2", .localisation)
        }
        .padding(.horizontal, 20)
        .frame(height: 49)
        .modifier(CiteDetailNavBackground())
    }

    private func item(_ icon: String, _ page: CiteDetailPage) -> some View {
        CiteDetailNavItem(icon: icon, selected: selected == page) { onTap(page) }
    }
}
