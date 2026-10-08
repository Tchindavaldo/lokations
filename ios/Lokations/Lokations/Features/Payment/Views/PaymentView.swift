import SwiftUI

// Reproduit res/layout/fragment_payement.xml (fragment_payement.kt, onglets ajoutés
// en code : orange money, mobile money, visa card, mastercard). Présenté en `.sheet`
// depuis CiteDetailView (R13). Marges 15/20/15.
// Corrections (R13) : données de la cité (prix, dates calculées), Location/Reservation
// et onglets actifs, contrat à accepter avant paiement, Telecharger partage le contrat,
// Payer demande confirmation.

struct PaymentView: View {
    let cite: Cite

    @Environment(\.dismiss) private var dismiss
    @State private var tab = 0
    @State private var reservation = false
    @State private var accepted = false
    @State private var tranches = false
    @State private var numero = ""
    @State private var confirm = false
    @State private var done = false

    private static let fraisElectricite = 15_000
    private static let fraisRetrait = 2_000
    private let methodes = ["orange money", "mobile money", "visa card", "mastercard"]

    private var mois: Int { reservation ? 1 : 12 }
    private var chambre: Int { cite.pricePerMonth * mois }
    private var total: Int { chambre + Self.fraisElectricite + Self.fraisRetrait }
    private var aPayer: Int { tranches ? (total + 2) / 3 : total }
    private var numeroLabel: String {
        switch tab {
        case 0: return "Numero OM"
        case 1: return "Numero MoMo"
        default: return "Numero de carte"
        }
    }
    private var peutPayer: Bool { accepted && numero.filter(\.isNumber).count >= 9 }

    var body: some View {
        VStack(spacing: 0) {
            PaymentHeader(cite: cite, prix: "\(f(cite.pricePerMonth)) /mois")

            HStack(alignment: .firstTextBaseline, spacing: 15) {
                mode("Location", actif: !reservation) { reservation = false }
                mode("Reservation", actif: reservation) { reservation = true }
                Spacer(minLength: 0)
            }
            .padding(.top, 20)

            PaymentTabs(titles: methodes, selected: $tab)
                .padding(.top, 20)

            ScrollView { content }
                .padding(.top, 25)
        }
        .padding(.horizontal, 15)
        .padding(.top, 20)
        .background(DS.white)
        .alert("Confirmer le paiement", isPresented: $confirm) {
            Button("Annuler", role: .cancel) {}
            Button("Payer \(f(aPayer))") { done = true }
        } message: {
            Text("\(cite.name) : \(f(aPayer)) via \(methodes[tab]) (\(numero)).")
        }
        .alert("Demande de paiement envoyée", isPresented: $done) {
            Button("OK") { dismiss() }
        } message: {
            Text("Validez la transaction sur votre téléphone.")
        }
    }

    private var content: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .top, spacing: 0) {
                column("Duré du bail", reservation ? "1 MOIS" : "1 ANS")
                column("Début", date(.now))
                column("Fin", date(Calendar.current.date(byAdding: .month, value: mois, to: .now) ?? .now))
            }

            contrat.padding(.top, 35)

            VStack(alignment: .leading, spacing: 5) {
                Text(numeroLabel)
                    .font(.system(size: 12))
                    .foregroundStyle(DS.black)
                    .padding(.leading, 5)
                TextField("696 08 00 87", text: $numero)
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                    .keyboardType(.numberPad)
                    .padding(.leading, 5)
                    .frame(height: 35)
                    .background(DS.blackA(5), in: RoundedRectangle(cornerRadius: 10))
            }
            .padding(.top, 30)

            HStack {
                Text("Payement en plusieurs tranches")
                    .font(.system(size: 12))
                    .foregroundStyle(DS.black)
                Spacer(minLength: 0)
                PaymentSwitch(isOn: $tranches)
            }
            .padding(.top, 30)

            Text("Montant")
                .font(.system(size: 12, weight: .bold))
                .foregroundStyle(DS.black)
                .padding(.top, 30)

            line("Chambre", f(chambre))
            line("Frais electricité", f(Self.fraisElectricite))
            line("Frais de retrait", f(Self.fraisRetrait))
            line("Total", f(total))
            if tranches { line("1re tranche (sur 3)", f(aPayer)) }

            Button { confirm = true } label: {
                Text("Payer Maintenant")
                    .font(.system(size: 14, weight: .bold))
                    .foregroundStyle(DS.white)
                    .padding(.horizontal, 10)
                    .padding(.vertical, 8)
                    .background(DS.black, in: RoundedRectangle(cornerRadius: 10)) // round_blue_10 = #000000
                    .opacity(peutPayer ? 1 : 0.4)
            }
            .buttonStyle(.plain)
            .disabled(!peutPayer)
            .frame(maxWidth: .infinity)
            .padding(.top, 30)
            .padding(.bottom, 3)
        }
    }

    private func f(_ value: Int) -> String {
        let nf = NumberFormatter()
        nf.numberStyle = .decimal
        nf.groupingSeparator = " "
        return (nf.string(from: NSNumber(value: value)) ?? "\(value)") + "f"
    }

    private func date(_ d: Date) -> String {
        let df = DateFormatter()
        df.dateFormat = "dd - MM - yyyy"
        return df.string(from: d)
    }

    private func mode(_ titre: String, actif: Bool, action: @escaping () -> Void) -> some View {
        Button(action: action) {
            Text(titre)
                .font(.system(size: 12, weight: actif ? .bold : .regular))
                .foregroundStyle(actif ? DS.black : DS.blackA(50))
                .padding(.horizontal, actif ? 7 : 0)
                .padding(.vertical, actif ? 5 : 0)
                .background(actif ? DS.android(0xE6E0E3) : DS.android(0x00FFFFFF, hasAlpha: true),
                            in: RoundedRectangle(cornerRadius: 10))
        }
        .buttonStyle(.plain)
    }

    private func column(_ label: String, _ value: String) -> some View {
        VStack(alignment: .leading, spacing: 2) {
            Text(label).font(.system(size: 12)).foregroundStyle(DS.black)
            Text(value).font(.system(size: 12)).foregroundStyle(DS.blackA(50))
        }
        .padding(.bottom, 2)
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    private var contratTexte: String {
        "Contrat de bail de location - \(cite.name), \(cite.district), \(cite.city). "
            + "Durée : \(reservation ? "1 mois" : "1 an") à compter du \(date(.now)). "
            + "Loyer : \(f(cite.pricePerMonth)) par mois."
    }

    private var contrat: some View {
        HStack(alignment: .bottom, spacing: 0) {
            Image("ic_baseline_shopping_bag_242")
                .resizable()
                .scaledToFill()
                .frame(width: 14, height: 14)
                .frame(width: 35, height: 35)
                .background(DS.black, in: RoundedRectangle(cornerRadius: 10))

            VStack(alignment: .leading, spacing: 0) {
                Text("Contrat de bail de location")
                    .font(.system(size: 12))
                    .foregroundStyle(DS.black)
                    .padding(.leading, 9)
                Spacer(minLength: 0)
                Button { accepted.toggle() } label: {
                    HStack(alignment: .bottom, spacing: 0) {
                        Text("j'accepte le contrat")
                            .font(.system(size: 12))
                            .foregroundStyle(DS.blackA(50))
                            .padding(.leading, 9)
                            .padding(.trailing, 10)
                        PaymentCheckBox(isOn: $accepted)
                    }
                }
                .buttonStyle(.plain)
                .padding(.bottom, 2)
            }
            .frame(height: 35)

            Spacer(minLength: 0)

            ShareLink(item: contratTexte) {
                Text("Telecharger")
                    .font(.system(size: 12))
                    .foregroundStyle(DS.black)
                    .padding(.horizontal, 7)
                    .padding(.vertical, 5)
                    .background(DS.blackA(5), in: RoundedRectangle(cornerRadius: 5))
            }
            .padding(.bottom, 2)
        }
    }

    private func line(_ label: String, _ value: String) -> some View {
        HStack(alignment: .firstTextBaseline) {
            Text(label).font(.system(size: 12)).foregroundStyle(DS.blackA(50))
            Spacer(minLength: 0)
            Text(value).font(.system(size: 12, weight: .bold)).foregroundStyle(DS.black)
        }
        .padding(.top, 20)
    }
}
