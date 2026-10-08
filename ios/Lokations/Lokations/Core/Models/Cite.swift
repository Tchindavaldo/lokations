import Foundation

/// Cité (résidence) affichée sur l'accueil, la recherche et le détail.
struct Cite: Identifiable, Hashable {
    let id: UUID
    var imageName: String
    var gallery: [String]
    var name: String
    var totalRooms: Int
    var freeRooms: Int
    var releasingRooms: Int
    var pricePerMonth: Int
    var city: String
    var district: String
    var summary: String

    init(id: UUID = UUID(), imageName: String, gallery: [String] = [], name: String,
         totalRooms: Int = 24, freeRooms: Int = 20, releasingRooms: Int = 4,
         pricePerMonth: Int, city: String, district: String, summary: String) {
        self.id = id
        self.imageName = imageName
        self.gallery = gallery.isEmpty ? [imageName] : gallery
        self.name = name
        self.totalRooms = totalRooms
        self.freeRooms = freeRooms
        self.releasingRooms = releasingRooms
        self.pricePerMonth = pricePerMonth
        self.city = city
        self.district = district
        self.summary = summary
    }
}

/// Section horizontale ("top qualité", ...).
struct CiteSection: Identifiable {
    let id = UUID()
    var title: String
    var cites: [Cite]
}
