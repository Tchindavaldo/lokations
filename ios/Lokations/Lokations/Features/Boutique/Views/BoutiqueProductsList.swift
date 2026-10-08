import SwiftUI

/// CRUD des produits Firestore (équivalent activity_ajout_produit + activity_update_produit).
struct BoutiqueProductsList: View {
    @EnvironmentObject private var store: BoutiqueStore
    @State private var editing: ProductEditorSheet.Target?

    var body: some View {
        List {
            ForEach(store.products) { product in
                Button { editing = .edit(product) } label: {
                    BoutiqueProductRow(product: product)
                }
                .swipeActions {
                    Button(role: .destructive) {
                        Task { await store.delete(product) }
                    } label: { Label("Supprimer", systemImage: "trash") }
                }
            }
        }
        .listStyle(.plain)
        .overlay {
            if store.isLoading && store.products.isEmpty {
                ProgressView()
            } else if store.products.isEmpty {
                Text("Aucun produit").foregroundStyle(DS.textSecondary)
            }
        }
        .refreshable { await store.load() }
        .task { await store.load() }
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button { editing = .create } label: { Image(systemName: "plus") }
                    .accessibilityLabel("Ajouter un produit")
            }
        }
        .sheet(item: $editing) { target in
            ProductEditorSheet(target: target)
                .presentationDetents([.medium])
        }
        .alert(store.feedback?.message ?? "",
               isPresented: Binding(get: { store.feedback?.isError == true },
                                    set: { if !$0 { store.feedback = nil } })) {
            Button("OK", role: .cancel) {}
        }
    }
}

struct BoutiqueProductRow: View {
    let product: Product

    var body: some View {
        HStack(spacing: DS.Space.md) {
            Image(systemName: "bed.double.fill")
                .foregroundStyle(DS.ink)
                .frame(width: 44, height: 44)
                .background(DS.surface, in: RoundedRectangle(cornerRadius: DS.Radius.sm))
            Text(product.item).font(.headline)
            Spacer()
            Text("\(product.prix) F").foregroundStyle(DS.textSecondary)
        }
        .foregroundStyle(DS.textPrimary)
    }
}
