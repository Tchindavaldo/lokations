import SwiftUI

// Equivalent des Toast Android (Toast.LENGTH_SHORT) affiches par fragment_boutique_boutique.kt,
// activity_ajout_produit.kt et activity_update_produit.kt : retour de BoutiqueStore.feedback.
struct BoutiqueToastModifier: ViewModifier {
    @EnvironmentObject private var store: BoutiqueStore
    @State private var message: String?

    func body(content: Content) -> some View {
        content
            .overlay(alignment: .bottom) {
                if let message {
                    Text(message)
                        .font(.system(size: 14))
                        .foregroundStyle(DS.white)
                        .padding(.horizontal, 16)
                        .padding(.vertical, 12)
                        .background(Capsule().fill(DS.android(0x323232)))
                        .padding(.horizontal, 24)
                        .padding(.bottom, 64)
                        .transition(.opacity)
                }
            }
            .onChange(of: store.feedback?.id) { _, newID in
                guard newID != nil, let feedback = store.feedback else { return }
                let text = feedback.message
                withAnimation { message = text }
                Task {
                    try? await Task.sleep(for: .seconds(2))
                    if message == text { withAnimation { message = nil } }
                }
            }
    }
}
