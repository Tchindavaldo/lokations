import SwiftUI

/// Reproduit `res/layout/fragment_param.xml` (fragment_param.kt) : fond #FFFFFF,
/// en-tête 50 "Parametre Et Confidentialité" (14 gras centré), puis 12 lignes ;
/// item1 à item10 apparaissent en fondu sur 2 s (anim `fade_in_bottom_nav`).
/// "Deconnexion" est relié à `SessionStore.signOut()`.
struct SettingsView: View {
    @EnvironmentObject private var session: SessionStore
    @State private var itemsAlpha: Double = 0

    /// (icône, teinte Android, libellé, marge haute) dans l'ordre du XML.
    private let items: [SettingsItemData] = [
        .init(icon: "ic_baseline_person_outline_24", tint: DS.white, title: "Compte", marginTop: 10),
        .init(icon: "ic_baseline_lock_24", tint: DS.white, title: "Securité", marginTop: 0),
        .init(icon: "ic_baseline_shield_24", tint: DS.black, title: "Confidentialité", marginTop: 10),
        .init(icon: "notif", tint: DS.android(0x25121F), title: "Notification", marginTop: 10),
        .init(icon: "ic_baseline_shopping_bag_24", tint: DS.black, title: "Langue", marginTop: 10),
        .init(icon: "ic_baseline_mode_edit_24", tint: DS.black, title: "Vos Remarques", marginTop: 10),
        .init(icon: "ic_baseline_assistant_photo_24", tint: DS.black, title: "Assistance", marginTop: 10),
        .init(icon: "ic_baseline_directions_24", tint: DS.black, title: "Signaler Un Problème", marginTop: 10),
        .init(icon: "ic_baseline_call_24", tint: DS.black, title: "Contactez-Nous", marginTop: 10),
        .init(icon: "ic_baseline_error_24", tint: DS.black, title: "Condition Et POlitique", marginTop: 10),
        .init(icon: "ic_baseline_sync_alt_24", tint: DS.black, title: "Changer De Compte", marginTop: 10),
        .init(icon: "ic_baseline_logout_24", tint: DS.black, title: "Deconnexion", marginTop: 10),
    ]

    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                Text("Parametre Et Confidentialité")
                    .font(.system(size: 14, weight: .bold))
                    .foregroundStyle(DS.black)
                    .multilineTextAlignment(.center)
                    .frame(maxWidth: .infinity, minHeight: 50, maxHeight: 50)
                ForEach(Array(items.enumerated()), id: \.offset) { index, item in
                    SettingsRow(icon: item.icon, tint: item.tint, title: item.title)
                        .padding(.top, item.marginTop)
                        .opacity(index < 10 ? itemsAlpha : 1) // item1…item10 seulement
                        .contentShape(Rectangle())
                        .onTapGesture {
                            if item.title == "Deconnexion" { session.signOut() }
                        }
                }
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(DS.white)
        .onAppear {
            itemsAlpha = 0
            withAnimation(.easeInOut(duration: 2)) { itemsAlpha = 1 }
        }
    }
}

struct SettingsItemData {
    let icon: String
    let tint: Color
    let title: String
    let marginTop: CGFloat
}
