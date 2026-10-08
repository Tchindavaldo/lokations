import SwiftUI

// Composants de res/layout/fragment_payement.xml (dupliqués pour la feature Payment, R10).

/// En-tête : CardView 55 (m6), trois textes, pastille « Paiement » (round_black_10).
struct PaymentHeader: View {
    var body: some View {
        HStack(alignment: .bottom, spacing: 0) {
            Image("m6")
                .resizable()
                .scaledToFill()
                .frame(width: 55, height: 55)
                .background(DS.whiteA(50))
                .clipShape(Circle())

            VStack(alignment: .leading, spacing: 0) {
                Text("Cité Hypocrate")
                    .font(.system(size: 12, weight: .bold))
                    .foregroundStyle(DS.black)
                Spacer(minLength: 0)
                Text("Baganté").font(.system(size: 12)).foregroundStyle(DS.blackA(50))
                Text("Chambre10,  260 000/Ans")
                    .font(.system(size: 12))
                    .foregroundStyle(DS.blackA(50))
                    .padding(.bottom, 5)
            }
            .padding(.leading, 15)
            .padding(.bottom, 2)
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)

            Text("Paiement")
                .font(.system(size: 14, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(.horizontal, 10)
                .padding(.vertical, 8)
                .background(DS.black, in: RoundedRectangle(cornerRadius: 10))
        }
        .frame(height: 55)
    }
}

/// TabLayout scrollable (hauteur 20dp, style « size » 9sp en majuscules, indicateur noir
/// 2dp à la largeur du texte, largeur mini d'onglet 72dp, padding 0).
struct PaymentTabs: View {
    let titles: [String]
    @Binding var selected: Int

    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 0) {
                ForEach(titles.indices, id: \.self) { i in
                    Button { withAnimation(.easeInOut(duration: 0.25)) { selected = i } } label: {
                        Text(titles[i].uppercased())
                            .font(.system(size: 12, weight: .medium))
                            .foregroundStyle(DS.black)
                            .fixedSize()
                            .overlay(alignment: .bottom) {
                                if selected == i {
                                    Rectangle().fill(DS.black).frame(height: 2).offset(y: 5)
                                }
                            }
                            .frame(minWidth: 72, maxHeight: .infinity)
                    }
                    .buttonStyle(.plain)
                }
            }
        }
        .frame(height: 20)
        .padding(.leading, -2)
    }
}

/// CheckBox 13x13, fond noir, buttonTint blanc.
struct PaymentCheckBox: View {
    @Binding var isOn: Bool

    var body: some View {
        Button { isOn.toggle() } label: {
            ZStack {
                Rectangle().fill(DS.black)
                if isOn {
                    RoundedRectangle(cornerRadius: 1).fill(DS.white).padding(2)
                    Image(systemName: "checkmark")
                        .font(.system(size: 6, weight: .heavy))
                        .foregroundStyle(DS.black)
                } else {
                    RoundedRectangle(cornerRadius: 1).stroke(DS.white, lineWidth: 1.5).padding(2.5)
                }
            }
            .frame(width: 13, height: 13)
        }
        .buttonStyle(.plain)
    }
}

/// SwitchMaterial (thème : colorSecondary teal_200) : piste 34x14, pouce 20.
struct PaymentSwitch: View {
    @Binding var isOn: Bool

    var body: some View {
        Button { withAnimation(.easeInOut(duration: 0.15)) { isOn.toggle() } } label: {
            ZStack(alignment: isOn ? .trailing : .leading) {
                Capsule()
                    .fill(isOn ? DS.teal200.opacity(0.54) : DS.blackA(38))
                    .frame(width: 34, height: 14)
                Circle()
                    .fill(isOn ? DS.teal200 : DS.white)
                    .frame(width: 20, height: 20)
                    .shadow(color: DS.blackA(30), radius: 1, y: 1)
                    .padding(.horizontal, -3)
            }
            .frame(width: 34, height: 20)
        }
        .buttonStyle(.plain)
    }
}
