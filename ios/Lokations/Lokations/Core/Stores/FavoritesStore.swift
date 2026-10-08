import Foundation

/// Cités favorites, persistées localement (préférence utilisateur, pas d'état temps réel).
@MainActor
final class FavoritesStore: ObservableObject {
    @Published private(set) var names: Set<String>

    private let key = "favorites.cites"

    init() {
        names = Set(UserDefaults.standard.stringArray(forKey: key) ?? [])
    }

    func contains(_ cite: Cite) -> Bool { names.contains(cite.name) }

    func toggle(_ cite: Cite) {
        if names.contains(cite.name) { names.remove(cite.name) } else { names.insert(cite.name) }
        UserDefaults.standard.set(Array(names), forKey: key)
    }
}
