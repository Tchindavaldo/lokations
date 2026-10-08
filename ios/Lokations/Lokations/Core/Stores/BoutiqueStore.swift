import Foundation

/// Source de vérité des produits de la boutique (R6). Toute écriture renvoie un feedback (R11).
@MainActor
final class BoutiqueStore: ObservableObject {
    @Published private(set) var products: [Product] = []
    @Published private(set) var isLoading = false
    @Published var feedback: Feedback?

    struct Feedback: Identifiable {
        let id = UUID()
        let message: String
        let isError: Bool
    }

    private let service: ProductServicing

    init(service: ProductServicing) {
        self.service = service
    }

    func load() async {
        isLoading = true
        defer { isLoading = false }
        do {
            products = try await service.fetch()
        } catch {
            feedback = Feedback(message: "Erreur de chargement : \(error.localizedDescription)", isError: true)
        }
    }

    func add(_ product: Product) async -> Bool {
        await commit(products + [product], success: "Produit ajouté")
    }

    func update(_ product: Product) async -> Bool {
        guard let index = products.firstIndex(where: { $0.id == product.id }) else { return false }
        var updated = products
        updated[index] = product
        return await commit(updated, success: "Produit mis à jour")
    }

    func delete(_ product: Product) async -> Bool {
        await commit(products.filter { $0.id != product.id }, success: "Produit supprimé")
    }

    private func commit(_ updated: [Product], success: String) async -> Bool {
        do {
            try await service.save(updated)
            products = updated
            feedback = Feedback(message: success, isError: false)
            return true
        } catch {
            feedback = Feedback(message: "Erreur : \(error.localizedDescription)", isError: true)
            return false
        }
    }
}
