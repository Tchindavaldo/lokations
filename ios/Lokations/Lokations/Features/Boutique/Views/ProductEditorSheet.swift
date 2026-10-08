import SwiftUI

/// Formulaire ajout / modification d'un produit.
struct ProductEditorSheet: View {
    enum Target: Identifiable {
        case create
        case edit(Product)

        var id: String {
            switch self {
            case .create: "create"
            case .edit(let product): product.id.uuidString
            }
        }
    }

    let target: Target

    @EnvironmentObject private var store: BoutiqueStore
    @Environment(\.dismiss) private var dismiss
    @State private var item = ""
    @State private var prix = ""
    @State private var isSaving = false

    private var isValid: Bool {
        !item.trimmingCharacters(in: .whitespaces).isEmpty && Int(prix) != nil
    }

    var body: some View {
        NavigationStack {
            Form {
                TextField("Nom (ex. chambre 3)", text: $item)
                TextField("Prix mensuel (FCFA)", text: $prix)
                    .keyboardType(.numberPad)
                if case .edit(let product) = target {
                    Button("Supprimer", role: .destructive) {
                        Task { await run { await store.delete(product) } }
                    }
                }
            }
            .navigationTitle(title)
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) { Button("Annuler") { dismiss() } }
                ToolbarItem(placement: .confirmationAction) {
                    Button("Enregistrer") { Task { await run(save) } }
                        .disabled(!isValid || isSaving)
                }
            }
            .onAppear(perform: prefill)
            .disabled(isSaving)
        }
    }

    private var title: String {
        if case .edit = target { return "Modifier" }
        return "Nouveau produit"
    }

    private func prefill() {
        guard case .edit(let product) = target else { return }
        item = product.item
        prix = product.prix
    }

    private func save() async -> Bool {
        switch target {
        case .create:
            return await store.add(Product(item: item, prix: prix))
        case .edit(var product):
            product.item = item
            product.prix = prix
            return await store.update(product)
        }
    }

    private func run(_ action: () async -> Bool) async {
        isSaving = true
        let ok = await action()
        isSaving = false
        if ok { dismiss() }
    }
}
