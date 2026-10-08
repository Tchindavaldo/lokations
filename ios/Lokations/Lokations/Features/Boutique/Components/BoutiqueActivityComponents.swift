import SwiftUI
import Charts

/// Historique des transactions (équivalent fragment_boutique_historique_transcastion).
struct BoutiqueHistoryList: View {
    private let transactions = MockData.transactions

    var body: some View {
        List(transactions) { transaction in
            HStack {
                VStack(alignment: .leading, spacing: DS.Space.xs) {
                    Text(transaction.label).font(.subheadline)
                    Text(transaction.date, style: .date)
                        .font(.caption)
                        .foregroundStyle(DS.textSecondary)
                }
                Spacer()
                Text("\(transaction.amount > 0 ? "+" : "")\(transaction.amount.formatted()) F")
                    .font(.subheadline.bold())
                    .foregroundStyle(transaction.amount >= 0 ? DS.success : DS.danger)
            }
        }
        .listStyle(.plain)
    }
}

/// Copie dédiée boutique (R16) : statistiques de revenus.
struct BoutiqueStatsView: View {
    @State private var period: StatPeriod = .monthly

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: DS.Space.md) {
                HStack(spacing: DS.Space.md) {
                    BoutiqueKpiTile(title: "Revenus", value: "85 000 F")
                    BoutiqueKpiTile(title: "Occupation", value: "83 %")
                }
                Picker("Période", selection: $period) {
                    ForEach(StatPeriod.allCases) { Text($0.rawValue).tag($0) }
                }
                .pickerStyle(.segmented)
                Chart(MockData.stats(for: period)) { point in
                    LineMark(x: .value("Période", point.label), y: .value("Réservations", point.value))
                        .foregroundStyle(DS.accent)
                        .interpolationMethod(.catmullRom)
                    AreaMark(x: .value("Période", point.label), y: .value("Réservations", point.value))
                        .foregroundStyle(DS.accentAlpha(0.15))
                        .interpolationMethod(.catmullRom)
                }
                .frame(height: 220)
            }
            .padding(DS.Space.md)
        }
    }
}

struct BoutiqueKpiTile: View {
    let title: String
    let value: String

    var body: some View {
        VStack(alignment: .leading, spacing: DS.Space.xs) {
            Text(title).font(.caption).foregroundStyle(DS.textSecondary)
            Text(value).font(.title2.bold())
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(DS.Space.md)
        .background(DS.surface, in: RoundedRectangle(cornerRadius: DS.Radius.md))
    }
}
