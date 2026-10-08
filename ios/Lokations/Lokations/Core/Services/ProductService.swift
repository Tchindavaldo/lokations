import Foundation
import FirebaseFirestore

/// Accès Firestore pur pour la boutique (sans état).
protocol ProductServicing {
    func fetch() async throws -> [Product]
    func save(_ products: [Product]) async throws
}

struct FirestoreProductService: ProductServicing {
    private var docRef: DocumentReference {
        Firestore.firestore()
            .collection(AppConfig.Firestore.collection)
            .document(AppConfig.Firestore.document)
    }

    func fetch() async throws -> [Product] {
        let snapshot = try await docRef.getDocument()
        let list = snapshot.data()?[AppConfig.Firestore.listField] as? [[String: Any]] ?? []
        return list.map(Product.init(firestore:))
    }

    func save(_ products: [Product]) async throws {
        try await docRef.setData([AppConfig.Firestore.listField: products.map(\.firestoreValue)])
    }
}

final class DemoProductService: ProductServicing {
    private var products = MockData.products

    func fetch() async throws -> [Product] { products }
    func save(_ products: [Product]) async throws { self.products = products }
}
