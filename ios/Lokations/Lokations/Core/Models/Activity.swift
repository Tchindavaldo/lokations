import Foundation

struct AppNotification: Identifiable {
    let id = UUID()
    var imageName: String
    var title: String
    var subtitle: String
    var detail: String
}

struct Transaction: Identifiable {
    let id = UUID()
    var label: String
    var amount: Int
    var date: Date
}

enum StatPeriod: String, CaseIterable, Identifiable {
    case daily = "Journalier"
    case weekly = "Hebdo"
    case monthly = "Mensuel"
    case yearly = "Annuel"

    var id: String { rawValue }
}

struct StatPoint: Identifiable {
    let id = UUID()
    var label: String
    var value: Double
}
