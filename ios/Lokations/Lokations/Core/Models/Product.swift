import Foundation

/// Produit de la boutique. Schéma Firestore identique à Android :
/// users/user -> listOfUsers = [{ item, prix }]
struct Product: Identifiable, Hashable {
    let id = UUID()
    var item: String
    var prix: String

    init(item: String, prix: String) {
        self.item = item
        self.prix = prix
    }

    init(firestore dict: [String: Any]) {
        item = (dict["item"] as? String) ?? (dict["email"] as? String) ?? ""
        prix = (dict["prix"] as? String) ?? ""
    }

    var firestoreValue: [String: Any] { ["item": item, "prix": prix] }
}
