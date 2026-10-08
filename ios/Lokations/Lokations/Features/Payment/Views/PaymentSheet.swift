import SwiftUI

/// Paiement d'une réservation (équivalent fragment_payement).
/// NOTE : aucune clé de paiement côté app ; l'appel réel passera par le backend.
struct PaymentSheet: View {
    let cite: Cite

    enum Method: String, CaseIterable, Identifiable {
        case orange = "Orange Money", mtn = "MTN Mobile Money", card = "Carte bancaire"
        var id: String { rawValue }
    }

    @Environment(\.dismiss) private var dismiss
    @State private var method: Method = .orange
    @State private var phone = ""
    @State private var months = 1
    @State private var isSubmitted = false

    private var total: Int { cite.pricePerMonth * months }

    var body: some View {
        NavigationStack {
            Form {
                Section("Réservation") {
                    LabeledContent("Cité", value: cite.name)
                    Stepper("\(months) mois", value: $months, in: 1...12)
                    LabeledContent("Total", value: "\(total.formatted()) FCFA")
                        .fontWeight(.semibold)
                }
                Section("Paiement") {
                    Picker("Méthode", selection: $method) {
                        ForEach(Method.allCases) { Text($0.rawValue).tag($0) }
                    }
                    if method != .card {
                        TextField("Numéro de téléphone", text: $phone)
                            .keyboardType(.phonePad)
                            .textContentType(.telephoneNumber)
                    }
                }
                Section {
                    Button("Payer \(total.formatted()) FCFA") { isSubmitted = true }
                        .disabled(method != .card && phone.count < 9)
                }
            }
            .navigationTitle("Paiement")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) { Button("Fermer") { dismiss() } }
            }
            .alert("Demande de paiement envoyée", isPresented: $isSubmitted) {
                Button("OK") { dismiss() }
            } message: {
                Text("Validez la transaction sur votre téléphone.")
            }
        }
    }
}
