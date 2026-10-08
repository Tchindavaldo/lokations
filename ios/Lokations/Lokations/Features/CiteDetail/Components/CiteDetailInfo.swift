import Foundation

// Données d'une cité mises en forme pour le détail (ex-extras d'intent Android).

/// Données passées en extras d'intent (categori, lieux, ItemCategorie, prix).
struct CiteDetailInfo {
    let cite: Cite
    let categorie: String
    let itemCategorie: String
    let lieux: String
    let prix: String

    /// Numéro de contact de démonstration (le modèle Cite n'en porte pas encore).
    static let contactPhone = "+237696080087"

    var gallery: [String] { cite.gallery }
    var adresse: String { "\(cite.district), \(cite.city)" }
    var mensuel: String { "\(Self.grouped(cite.pricePerMonth)) FCFA / mois" }
    var telURL: URL? { URL(string: "tel:\(Self.contactPhone)") }
    var mapsURL: URL? {
        var c = URLComponents(string: "http://maps.apple.com/")
        c?.queryItems = [URLQueryItem(name: "q", value: "\(cite.name), \(adresse)")]
        return c?.url
    }

    init(cite: Cite) {
        self.cite = cite
        categorie = cite.name
        itemCategorie = "\(cite.totalRooms) chambres"
        lieux = "\(cite.city), \(cite.district)"
        prix = "\(Self.grouped(cite.pricePerMonth)) /mois"
    }

    static func grouped(_ value: Int) -> String {
        let f = NumberFormatter()
        f.numberStyle = .decimal
        f.groupingSeparator = " "
        f.groupingSize = 3
        return f.string(from: NSNumber(value: value)) ?? "\(value)"
    }
}
