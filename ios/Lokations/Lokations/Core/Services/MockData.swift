import Foundation

/// Données de démonstration (reprises de la version Android, qui est elle-même en dur).
enum MockData {
    static let gallery = ["m88", "m3", "m4", "m6", "m7", "m8", "m91"]

    private static let summary = "Cité moderne et sécurisée : eau, électricité, parking et gardiennage 24h/24, proche des commodités."

    static let hypocrate: [Cite] = [
        Cite(imageName: "m88", gallery: ["m88", "m3", "m4"], name: "cité hypocratea", pricePerMonth: 30000, city: "Douala", district: "Bonapriso", summary: summary),
        Cite(imageName: "m3", gallery: ["m3", "m88", "m6"], name: "cité hypocrateb", pricePerMonth: 35000, city: "Yaoundé", district: "Bastos", summary: summary),
        Cite(imageName: "m4", gallery: ["m4", "m7", "m8"], name: "cité hypocrate", pricePerMonth: 28000, city: "Douala", district: "Akwa", summary: summary),
    ]

    static let rose: [Cite] = [
        Cite(imageName: "m6", gallery: ["m6", "m7"], name: "cité rose", pricePerMonth: 20000, city: "Douala", district: "Makepe", summary: summary),
        Cite(imageName: "m7", gallery: ["m7", "m8"], name: "cité rose II", pricePerMonth: 22000, city: "Yaoundé", district: "Ngoa-Ekelle", summary: summary),
        Cite(imageName: "m8", gallery: ["m8", "m91"], name: "cité rose III", pricePerMonth: 18000, city: "Douala", district: "Logpom", summary: summary),
        Cite(imageName: "m91", gallery: ["m91", "m88"], name: "cité des anges", pricePerMonth: 40000, city: "Douala", district: "Bonapriso", summary: summary),
    ]

    static var allCites: [Cite] { hypocrate + rose }

    static let homeSections: [CiteSection] = [
        CiteSection(title: "top qualité", cites: hypocrate),
        CiteSection(title: "les moins chers", cites: rose.sorted { $0.pricePerMonth < $1.pricePerMonth }),
        CiteSection(title: "nouveautés", cites: hypocrate.reversed()),
        CiteSection(title: "populaires", cites: rose.reversed()),
    ]

    static let notifications: [AppNotification] = (0..<8).map { i in
        AppNotification(imageName: gallery[i % gallery.count], title: "Nouvelle Cité",
                        subtitle: "construction terminée",
                        detail: "de la cité des anges à Douala au quartier Bonapriso")
    }

    static let transactions: [Transaction] = [
        Transaction(label: "Location chambre 3 - cité rose", amount: 20000, date: .now.addingTimeInterval(-86_400 * 3)),
        Transaction(label: "Location chambre 7 - cité hypocrate", amount: 30000, date: .now.addingTimeInterval(-86_400 * 10)),
        Transaction(label: "Publicité boutique", amount: -5000, date: .now.addingTimeInterval(-86_400 * 15)),
        Transaction(label: "Location chambre 1 - cité des anges", amount: 40000, date: .now.addingTimeInterval(-86_400 * 22)),
    ]

    static let products: [Product] = [
        Product(item: "chambre 1", prix: "25000"),
        Product(item: "chambre 2", prix: "30000"),
    ]

    static func stats(for period: StatPeriod) -> [StatPoint] {
        switch period {
        case .daily:
            return zip(["L", "M", "M", "J", "V", "S", "D"], [3.0, 5, 2, 6, 4, 7, 5]).map { StatPoint(label: $0, value: $1) }
        case .weekly:
            return zip(["S1", "S2", "S3", "S4"], [12.0, 18, 15, 22]).map { StatPoint(label: $0, value: $1) }
        case .monthly:
            return zip(["Jan", "Fév", "Mar", "Avr", "Mai", "Juin"], [40.0, 55, 48, 62, 70, 58]).map { StatPoint(label: $0, value: $1) }
        case .yearly:
            return zip(["2022", "2023", "2024"], [420.0, 510, 600]).map { StatPoint(label: $0, value: $1) }
        }
    }
}
