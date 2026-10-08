import SwiftUI

// Reproduit res/layout/fragment_chambre_info1.xml (ViewPager2 de
// adapteur_fragment_slide_inSlide_chambre_info : info3, info3, info3, info4),
// fragment_chambre_info2.xml, fragment_chambre_info3.xml / inflate_chambre_infos.xml
// (adapteur_recycleView_chambre_info, texte « item ») et fragment_chambre_info4.xml.
// Les LinearLayout 0dp x 0dp (Montant/Statut/Contrat/Expiration) sont invisibles sur
// Android et ne sont donc pas dessinés.

/// fragment_chambre_info1 : pager horizontal sans indicateur.
struct CiteChambreInfoPager: View {
    @State private var index = 0
    @State private var drag: CGFloat = 0

    var body: some View {
        GeometryReader { geo in
            let w = geo.size.width
            HStack(alignment: .top, spacing: 0) {
                CiteChambreInfoRow(item: "Chambre 1").frame(width: w)
                CiteChambreInfoRow(item: "Chambre 1").frame(width: w)
                CiteChambreInfoRow(item: "Chambre 1").frame(width: w)
                CiteChambreInfo4View().frame(width: w)
            }
            .offset(x: -CGFloat(index) * w + drag)
            .contentShape(Rectangle())
            .gesture(
                DragGesture()
                    .onChanged { drag = $0.translation.width }
                    .onEnded { value in
                        var next = index
                        if value.predictedEndTranslation.width < -w / 2 { next += 1 }
                        if value.predictedEndTranslation.width > w / 2 { next -= 1 }
                        withAnimation(.easeOut(duration: 0.3)) {
                            index = min(max(next, 0), 3)
                            drag = 0
                        }
                    }
            )
        }
        .frame(height: 230)
        .clipped()
        .padding(.top, 5)
    }
}

/// fragment_chambre_info3 / inflate_chambre_infos.
struct CiteChambreInfoRow: View {
    let item: String

    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(spacing: 0) {
                Image("m7")
                    .resizable()
                    .scaledToFill()
                    .frame(width: 90, height: 85)
                    .clipShape(RoundedRectangle(cornerRadius: 15))

                VStack(alignment: .leading, spacing: 0) {
                    Text(item)
                        .font(.system(size: 12, weight: .bold))
                        .foregroundStyle(DS.black)
                        .padding(.bottom, 5)
                    Spacer(minLength: 0)
                    HStack(spacing: 5) {
                        label("loyer", 12); value("400 000/Ans")
                    }
                    .padding(.bottom, 4)
                    HStack(spacing: 0) {
                        label("Payer", 12)
                        value("400 000").padding(.leading, 5)
                        label("Impayé", 12).padding(.leading, 8)
                        value("00 000f").padding(.leading, 5)
                    }
                    .padding(.bottom, 4)
                    HStack(spacing: 5) {
                        label("Locataire", 12); value("Tchinda Valdo blair")
                    }
                    .padding(.bottom, 5)
                }
                .lineLimit(1)
                .minimumScaleFactor(0.7)
                .padding(.leading, 5)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)

                VStack(spacing: 0) {
                    Text("Occupé")
                        .font(.system(size: 12, weight: .bold))
                        .foregroundStyle(DS.android(0x004288))
                    Spacer(minLength: 0)
                    VStack(spacing: 5) {
                        Text("FIn Contrat").foregroundStyle(DS.blackA(50))
                        Text("05 - 08 - 2025").foregroundStyle(DS.black)
                    }
                    .font(.system(size: 12, weight: .bold))
                    .padding(.horizontal, 7)
                    .padding(.vertical, 5)
                    .background(DS.blackA(5), in: RoundedRectangle(cornerRadius: 15))
                }
                .frame(maxHeight: .infinity)
            }
            .frame(height: 85)

            HStack(spacing: 5) {
                ForEach(["rappel", "Contacter", "Modifier", "Résilier"], id: \.self) { action in
                    CiteChambreInfoLink(action: action) {
                    Text(action)
                        .font(.system(size: 12))
                        .foregroundStyle(DS.black)
                        .padding(.horizontal, 7)
                        .padding(.vertical, 3)
                        .background(DS.blackA(10), in: RoundedRectangle(cornerRadius: 15))
                    }
                }
            }
            .padding(.top, 5)
        }
        .padding(.horizontal, 5)
        .padding(.bottom, 20)
    }

    private func label(_ text: String, _ size: CGFloat) -> some View {
        Text(text).font(.system(size: size)).foregroundStyle(DS.black)
    }

    private func value(_ text: String) -> some View {
        Text(text).font(.system(size: 12, weight: .bold)).foregroundStyle(DS.blackA(50))
    }
}

/// fragment_chambre_info2 : carte round_e6e0e3_15.
struct CiteChambreInfo2View: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            VStack(spacing: 0) {
                HStack {
                    Text("Chambre 1")
                        .font(.system(size: 12, weight: .bold))
                        .foregroundStyle(DS.black)
                        .padding(.horizontal, 10)
                        .padding(.vertical, 5)
                        .background(DS.blackA(10), in: RoundedRectangle(cornerRadius: 15))
                    Spacer(minLength: 0)
                    Text("Occupé")
                        .font(.system(size: 12, weight: .bold))
                        .foregroundStyle(DS.android(0x004288))
                }
                .padding(.leading, 5)
                row("Loyer", "400 000/Ans")
                row("payé", "400 000f")
                row("Impayé", "00 000F")
                row("Fin de Contrat", "05 - 08 - 2025")
            }
            .padding(.trailing, 15)
            .padding(.vertical, 10)
            .background(DS.android(0xE6E0E3), in: RoundedRectangle(cornerRadius: 15))

            HStack(spacing: 5) {
                ForEach(["rappel", "Modifier", "Locataire"], id: \.self) { CiteChambreInfoAction(text: $0) }
            }
            .padding(.top, 5)
            .padding(.leading, 15)
        }
        .padding(.bottom, 45)
    }

    private func row(_ label: String, _ value: String) -> some View {
        HStack {
            Text(label).font(.system(size: 12)).foregroundStyle(DS.black)
            Spacer(minLength: 0)
            Text(value).font(.system(size: 12, weight: .bold)).foregroundStyle(DS.blackA(50))
        }
        .padding(.leading, 15)
        .padding(.top, 12)
    }
}

/// Pastille d'action round_e6e0e3_15, 9sp bold, padding 10/5.
struct CiteChambreInfoAction: View {
    let text: String

    var body: some View {
        CiteChambreInfoLink(action: text) { label }
    }

    private var label: some View {
        Text(text)
            .font(.system(size: 12, weight: .bold))
            .foregroundStyle(DS.black)
            .padding(.horizontal, 10)
            .padding(.vertical, 5)
            .background(DS.android(0xE6E0E3), in: RoundedRectangle(cornerRadius: 15))
    }
}

/// fragment_chambre_info4.
struct CiteChambreInfo4View: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            VStack(spacing: 0) {
                row("Chambre 1", "Occupé", valueColor: DS.android(0x004288)).padding(.top, -10)
                row("Fin de Contrat", "05 - 08 - 2025")
                row("Duré du Contrat", "1 Ans")
                row("Locataire", "Tchinda Valdo Blair")
                montant.padding(.top, 15)
            }
            .padding(.trailing, 15)
            .padding(.vertical, 10)

            HStack(spacing: 5) {
                ForEach(["rappel", "Contacter", "Modifier", "Résilier"], id: \.self) { CiteChambreInfoAction(text: $0) }
            }
            .padding(.top, 5)

            Rectangle().fill(DS.blackA(70)).frame(height: 1).padding(.top, 10)
        }
        .padding(.bottom, 35)
    }

    private func row(_ label: String, _ value: String, valueColor: Color = DS.blackA(50)) -> some View {
        HStack {
            Text(label).foregroundStyle(DS.black)
            Spacer(minLength: 0)
            Text(value).foregroundStyle(valueColor)
        }
        .font(.system(size: 12, weight: .bold))
        .padding(.leading, 15)
        .padding(.top, 10)
    }

    private var montant: some View {
        HStack(spacing: 0) {
            Image("ic_baseline_shopping_bag_24")
                .resizable()
                .scaledToFill()
                .frame(width: 22, height: 22)
                .frame(width: 45, height: 46)
                .padding(.leading, 15)

            VStack(alignment: .leading, spacing: 0) {
                Text("Montant").foregroundStyle(DS.black).padding(.top, 3)
                Spacer(minLength: 0)
                Text("400 000f/Ans").foregroundStyle(DS.blackA(50)).padding(.bottom, 3)
            }
            .font(.system(size: 12, weight: .bold))
            .padding(.leading, 15)
            .frame(maxHeight: .infinity)

            Spacer(minLength: 0)
            box("payé", "400 000f", DS.android(0x03CC17))
            box("Impayé", "0f", DS.android(0xD60012))
        }
        .frame(height: 46)
    }

    private func box(_ label: String, _ value: String, _ color: Color) -> some View {
        VStack(spacing: 5) {
            Text(label).foregroundStyle(DS.black)
            Text(value).foregroundStyle(color)
        }
        .font(.system(size: 12, weight: .bold))
        .padding(.top, -5)
        .padding(.horizontal, 12)
        .padding(.vertical, 7)
        .frame(width: 75)
        .background(DS.blackA(10), in: RoundedRectangle(cornerRadius: 15))
        .padding(.leading, 15)
    }
}

/// « Contacter » ouvre le composeur (tel:) au lieu d'un bouton inactif ; les autres
/// actions restent de simples pastilles tant qu'aucun flux n'existe côté iOS.
struct CiteChambreInfoLink<Label: View>: View {
    let action: String
    @ViewBuilder let label: () -> Label

    var body: some View {
        if action == "Contacter", let url = URL(string: "tel:\(CiteDetailInfo.contactPhone)") {
            Link(destination: url, label: label)
        } else {
            label()
        }
    }
}
