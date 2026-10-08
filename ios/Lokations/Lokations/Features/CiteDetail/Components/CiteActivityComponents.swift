import SwiftUI
import Charts

/// Avis (équivalent fragment_comment).
struct CiteReviewsTab: View {
    @State private var draft = ""
    @State private var reviews = ["Très propre et calme.", "Bon rapport qualité/prix."]

    var body: some View {
        VStack(alignment: .leading, spacing: DS.Space.md) {
            ForEach(reviews.indices, id: \.self) { i in
                HStack(alignment: .top, spacing: DS.Space.sm) {
                    Image(systemName: "person.crop.circle.fill")
                        .font(.title2)
                        .foregroundStyle(DS.textSecondary)
                    Text(reviews[i])
                }
            }
            HStack {
                TextField("Votre avis", text: $draft)
                    .textFieldStyle(.roundedBorder)
                    .onSubmit(send)
                Button("Envoyer", action: send)
                    .disabled(draft.trimmingCharacters(in: .whitespaces).isEmpty)
            }
        }
    }

    private func send() {
        let text = draft.trimmingCharacters(in: .whitespaces)
        guard !text.isEmpty else { return }
        reviews.append(text)
        draft = ""
    }
}

/// Statistiques de visites (équivalent fragment_statistique_*), en Swift Charts.
struct CiteStatsTab: View {
    @State private var period: StatPeriod = .daily

    var body: some View {
        VStack(alignment: .leading, spacing: DS.Space.md) {
            Picker("Période", selection: $period) {
                ForEach(StatPeriod.allCases) { Text($0.rawValue).tag($0) }
            }
            .pickerStyle(.segmented)

            Chart(MockData.stats(for: period)) { point in
                BarMark(x: .value("Période", point.label), y: .value("Visites", point.value))
                    .foregroundStyle(DS.accent.gradient)
                    .cornerRadius(6)
            }
            .frame(height: 200)
            .animation(.easeInOut, value: period)

            Text("Nombre de visites").font(.caption).foregroundStyle(DS.textSecondary)
        }
    }
}
