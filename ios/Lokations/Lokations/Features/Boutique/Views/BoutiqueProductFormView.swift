import SwiftUI

// Reproduit : res/layout/activity_ajout_produit.xml (activity_ajout_produit.kt) et
// res/layout/activity_update_produit.xml (activity_update_produit.kt) : 5 champs 45dp
// @drawable/round_black_25_15, mentions 10sp, case 13dp, 2 boutons noirs rayon 25dp.
// Champ 1 = item, champ "Mot de pase" = prix, champ 2 (modification) = index a modifier.
struct BoutiqueProductFormView: View {
    enum Mode: String, Identifiable {
        case add, update
        var id: String { rawValue }
    }

    let mode: Mode

    @EnvironmentObject private var store: BoutiqueStore
    @State private var item = "Nom"
    @State private var second: String
    @State private var email = "Email"
    @State private var phone = "Numero Tel"
    @State private var prix = "Mot de pase"
    @State private var accepted = false

    init(mode: Mode) {
        self.mode = mode
        _second = State(initialValue: mode == .add ? "Prenom" : "id a modifié")
    }

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Inscriez vous pour continuer")
                .font(.system(size: 13, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(.bottom, 5)
            BoutiqueProductField(icon: "ic_baseline_person_outline_24", text: $item)
            BoutiqueProductField(icon: "ic_baseline_person_outline_24", text: $second).padding(.top, 20)
            BoutiqueProductField(icon: "ic_baseline_person_outline_24", text: $email).padding(.top, 20)
            BoutiqueProductField(icon: "ic_baseline_call2_24", text: $phone).padding(.top, 20)
            BoutiqueProductField(icon: "ic_baseline_lock_24", text: $prix,
                                 trailingIcon: "ic_baseline_visibility_24")
                .padding(.top, 20)
            terms.padding(.top, 10)
            HStack(spacing: 0) {
                switch mode {
                case .add:
                    button("Ajouter le produit") { await add() }
                    button("Ajouter set") { await add() }
                case .update:
                    button("modifier") { await modify() }
                    button("supprimer") { await remove() }
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.top, 35)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .background(DS.white)
        .modifier(BoutiqueToastModifier())
        .onAppear(perform: prefill)
    }

    private var terms: some View {
        HStack(alignment: .bottom, spacing: 0) {
            Text("En vous inscrivant vous acceptez nos conditions d'utilisation, notre politique de confidentialité et notre utilisation des cookies.")
                .font(.system(size: 10))
                .foregroundStyle(DS.whiteA(50))
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.trailing, 15)
            Button { accepted.toggle() } label: {
                Rectangle()
                    .fill(DS.black)
                    .frame(width: 13, height: 13)
                    .overlay {
                        if accepted {
                            Image(systemName: "checkmark")
                                .font(.system(size: 8, weight: .bold))
                                .foregroundStyle(DS.white)
                        }
                    }
            }
            .buttonStyle(.plain)
        }
    }

    private func button(_ title: String, action: @escaping () async -> Void) -> some View {
        Button {
            Task { await action() }
        } label: {
            Text(title)
                .font(.system(size: 15, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(10)
                .frame(height: 50)
                .background(DS.black)
                .clipShape(RoundedRectangle(cornerRadius: 25))
        }
        .buttonStyle(.plain)
    }

    // MARK: Comportement (Firestore via BoutiqueStore)

    /// activity_update_produit.kt : pre-remplit avec listOfUsers[0].
    private func prefill() {
        guard mode == .update, let first = store.products.first else { return }
        item = first.item
        prix = first.prix
    }

    private func add() async {
        _ = await store.add(Product(item: item, prix: prix))
    }

    private func targetIndex() -> Int? {
        let raw = second.trimmingCharacters(in: .whitespaces)
        guard let index = Int(raw), store.products.indices.contains(index) else {
            store.feedback = BoutiqueStore.Feedback(message: "erreur : id invalide", isError: true)
            return nil
        }
        return index
    }

    private func modify() async {
        guard let index = targetIndex() else { return }
        var product = store.products[index]
        product.item = item
        product.prix = prix
        _ = await store.update(product)
    }

    private func remove() async {
        guard let index = targetIndex() else { return }
        _ = await store.delete(store.products[index])
    }
}
