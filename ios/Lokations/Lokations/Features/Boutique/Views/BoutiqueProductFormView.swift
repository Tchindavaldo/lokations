import SwiftUI

// Reproduit : res/layout/activity_ajout_produit.xml (activity_ajout_produit.kt) et
// res/layout/activity_update_produit.xml (activity_update_produit.kt) : champs 45dp
// @drawable/round_black_25_15, mentions + case 13dp, boutons noirs rayon 25dp.
// Bugs Android corriges (R13) : titre/mentions lisibles, champs de test retires
// (seuls Nom = item et Prix = prix sont enregistres), un seul bouton d'ajout,
// modification du produit touche (plus de numero tape), validation sous condition.
struct BoutiqueProductFormView: View {
    enum Mode: Identifiable {
        case add
        case edit(Product)

        var id: String {
            switch self {
            case .add: "add"
            case .edit(let product): product.id.uuidString
            }
        }
    }

    let mode: Mode

    @EnvironmentObject private var store: BoutiqueStore
    @Environment(\.dismiss) private var dismiss
    @State private var item = ""
    @State private var prix = ""
    @State private var accepted = false
    @State private var isSaving = false

    private var isValid: Bool {
        !item.trimmingCharacters(in: .whitespaces).isEmpty
            && !prix.trimmingCharacters(in: .whitespaces).isEmpty
            && accepted && !isSaving
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(title)
                .font(.system(size: 13, weight: .bold))
                .foregroundStyle(DS.black)
                .padding(.bottom, 5)
            BoutiqueProductField(icon: "ic_baseline_person_outline_24", placeholder: "Nom", text: $item)
            BoutiqueProductField(icon: "ic_baseline_lock_24", placeholder: "Prix", text: $prix)
                .keyboardType(.numberPad)
                .padding(.top, 20)
            terms.padding(.top, 10)
            HStack(spacing: 0) {
                switch mode {
                case .add:
                    button("Ajouter le produit") { await store.add(Product(item: item, prix: prix)) }
                case .edit(let product):
                    button("modifier") {
                        var updated = product
                        updated.item = item
                        updated.prix = prix
                        return await store.update(updated)
                    }
                    button("supprimer", enabled: !isSaving) { await store.delete(product) }
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.top, 35)
        }
        .padding(.top, 20)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .background(DS.white)
        .modifier(BoutiqueToastModifier())
        .onAppear(perform: prefill)
    }

    private var title: String {
        if case .edit = mode { return "Modifier le produit" }
        return "Ajouter un produit"
    }

    private var terms: some View {
        HStack(alignment: .bottom, spacing: 0) {
            Text("En vous inscrivant vous acceptez nos conditions d'utilisation, notre politique de confidentialité et notre utilisation des cookies.")
                .font(.system(size: 12))
                .foregroundStyle(DS.blackA(50))
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.trailing, 15)
            Button { accepted.toggle() } label: {
                Rectangle()
                    .fill(DS.black)
                    .frame(width: 13, height: 13)
                    .overlay {
                        if accepted {
                            Image(systemName: "checkmark")
                                .font(.system(size: 9, weight: .bold))
                                .foregroundStyle(DS.white)
                        }
                    }
                    .padding(8)
                    .contentShape(Rectangle())
            }
            .buttonStyle(.plain)
            .padding(-8)
        }
    }

    private func button(_ label: String, enabled: Bool? = nil,
                        action: @escaping () async -> Bool) -> some View {
        let isEnabled = enabled ?? isValid
        return Button {
            Task {
                isSaving = true
                let ok = await action()
                isSaving = false
                if ok { dismiss() }
            }
        } label: {
            Text(label)
                .font(.system(size: 15, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(10)
                .frame(height: 50)
                .background(DS.black)
                .clipShape(RoundedRectangle(cornerRadius: 25))
                .opacity(isEnabled ? 1 : 0.5)
        }
        .buttonStyle(.plain)
        .disabled(!isEnabled)
    }

    private func prefill() {
        guard case .edit(let product) = mode else { return }
        item = product.item
        prix = product.prix
        accepted = true
    }
}
