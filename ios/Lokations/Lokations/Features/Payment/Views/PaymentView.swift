import SwiftUI

// Reproduit res/layout/fragment_payement.xml (fragment_payement.kt, onglets ajoutés
// en code : orange money, mobile money, visa card, mastercard). Présenté en `.sheet`
// depuis CiteDetailView (R13). Marges 15/20/15 ; marginBottom 56dp de la barre Android
// non reprise dans la feuille.

struct PaymentView: View {
    @State private var tab = 0
    @State private var accepted = false
    @State private var tranches = false
    @State private var numero = "696 08 00 87"

    var body: some View {
        VStack(spacing: 0) {
            PaymentHeader()

            HStack(alignment: .firstTextBaseline, spacing: 15) {
                Text("Location")
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                    .padding(.horizontal, 7)
                    .padding(.vertical, 5)
                    .background(DS.android(0xE6E0E3), in: RoundedRectangle(cornerRadius: 10))
                Text("Reservation").font(.system(size: 12)).foregroundStyle(DS.blackA(50))
                Spacer(minLength: 0)
            }
            .padding(.top, 20)

            PaymentTabs(titles: ["orange money", "mobile money", "visa card", "mastercard"],
                        selected: $tab)
                .padding(.top, 20)

            ScrollView { content }
                .padding(.top, 25)
        }
        .padding(.horizontal, 15)
        .padding(.top, 20)
        .background(DS.white)
    }

    private var content: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(alignment: .top, spacing: 0) {
                column("Duré du bail", "1 ANS")
                column("Début", "04 - 02 - 2025")
                column("Fin", "04 - 02 - 2025")
            }

            contrat.padding(.top, 35)

            VStack(alignment: .leading, spacing: 5) {
                Text("Numero OM")
                    .font(.system(size: 12))
                    .foregroundStyle(DS.black)
                    .padding(.leading, 5)
                TextField("", text: $numero)
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                    .keyboardType(.phonePad)
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

            line("Chambre", "200 000f")
            line("Frais electricité", "15 000f")
            line("Frais de retrait", "2 000f")
            line("Total", "217 000f")

            Text("Payer Maintenat")
                .font(.system(size: 14, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(.horizontal, 10)
                .padding(.vertical, 8)
                .background(DS.black, in: RoundedRectangle(cornerRadius: 10)) // round_blue_10 = #000000
                .frame(maxWidth: .infinity)
                .padding(.top, 30)
                .padding(.bottom, 3)
        }
    }

    private func column(_ label: String, _ value: String) -> some View {
        VStack(alignment: .leading, spacing: 2) {
            Text(label).font(.system(size: 12)).foregroundStyle(DS.black)
            Text(value).font(.system(size: 12)).foregroundStyle(DS.blackA(50))
        }
        .padding(.bottom, 2)
        .frame(maxWidth: .infinity, alignment: .leading)
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
                HStack(alignment: .bottom, spacing: 0) {
                    Text("j'accepte le contrat")
                        .font(.system(size: 12))
                        .foregroundStyle(DS.blackA(50))
                        .padding(.leading, 9)
                        .padding(.trailing, 10)
                    PaymentCheckBox(isOn: $accepted)
                }
                .padding(.bottom, 2)
            }
            .frame(height: 35)

            Spacer(minLength: 0)

            Text("Telecharger")
                .font(.system(size: 12))
                .foregroundStyle(DS.black)
                .padding(.horizontal, 7)
                .padding(.vertical, 5)
                .background(DS.blackA(5), in: RoundedRectangle(cornerRadius: 5))
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
