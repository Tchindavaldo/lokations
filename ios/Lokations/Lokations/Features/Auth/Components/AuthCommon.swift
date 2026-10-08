// Éléments communs à activity_login.xml et activity_register.xml :
// fond mblur3 + voile #B3000000, bouton CardView noir, séparateur « ou »,
// rangée de logos sociaux, switch Material, case à cocher, Toast.
import SwiftUI

/// ImageView mblur3 (centerCrop) + ConstraintLayout #B3000000.
struct AuthBackground: View {
    var body: some View {
        ZStack {
            Image("mblur3").resizable().scaledToFill()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .clipped()
            DS.android(0xB3000000, hasAlpha: true)
        }
        .ignoresSafeArea()
    }
}

/// CardView (rayon 25dp) contenant un TextView 50dp noir, texte blanc 15sp bold.
struct AuthBlackButton: View {
    let title: String
    var isLoading = false
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            Group {
                if isLoading { ProgressView().tint(DS.white) } else { Text(title) }
            }
                .font(.system(size: 15, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(10)
                .frame(maxWidth: .infinity)
                .frame(height: 50)
                .background(DS.black)
                .clipShape(RoundedRectangle(cornerRadius: 25))
        }
        .buttonStyle(.plain)
        .disabled(isLoading)
    }
}

/// Ligne 1dp white_50 — « ou » blanc 16sp bold — ligne 1dp white_50.
struct AuthOrDivider: View {
    var body: some View {
        HStack(spacing: 0) {
            Rectangle().fill(DS.android(0x80FFFFFF, hasAlpha: true))
                .frame(height: 1).padding(.trailing, 5)
            Text("ou ")
                .font(.system(size: 16, weight: .bold))
                .foregroundStyle(DS.white)
                .fixedSize()
            Rectangle().fill(DS.android(0x80FFFFFF, hasAlpha: true))
                .frame(height: 1).padding(.leading, 5)
        }
    }
}

/// Logos google / facebook / apple / whatsapp 20x20dp avec leurs marges XML.
/// Connexion sociale non disponible : chaque logo déclenche `onTap` (toast).
struct AuthSocialRow: View {
    let onTap: () -> Void

    var body: some View {
        HStack(spacing: 0) {
            logo("logo_google")
            logo("logo_facebook").padding(.leading, 25).padding(.trailing, 15)
            logo("logo_apple_svg").padding(.leading, 15).padding(.trailing, 25)
            logo("logo_whatsapp_svg")
        }
    }

    private func logo(_ name: String) -> some View {
        Image(name).resizable().scaledToFit().frame(width: 20, height: 20)
            .contentShape(Rectangle().inset(by: -10))
            .onTapGesture(perform: onTap)
    }
}

/// SwitchMaterial (hauteur 20dp) : piste 34x14, pouce 20dp.
struct AuthMaterialSwitch: View {
    @Binding var isOn: Bool

    var body: some View {
        ZStack(alignment: isOn ? .trailing : .leading) {
            Capsule()
                .fill(isOn ? DS.teal200.opacity(0.54) : DS.blackA(38))
                .frame(width: 34, height: 14)
                .frame(width: 40)
            Circle()
                .fill(isOn ? DS.teal200 : DS.android(0xFAFAFA))
                .frame(width: 20, height: 20)
                .shadow(color: DS.blackA(30), radius: 1, y: 1)
        }
        .frame(height: 20)
        .contentShape(Rectangle())
        .onTapGesture { withAnimation(.easeInOut(duration: 0.15)) { isOn.toggle() } }
    }
}

/// CheckBox 13x13dp, fond noir, buttonTint blanc.
struct AuthCheckBox: View {
    @Binding var isOn: Bool

    var body: some View {
        ZStack {
            DS.black
            RoundedRectangle(cornerRadius: 2)
                .stroke(DS.white, lineWidth: 1.5)
                .padding(1.5)
            if isOn {
                Image(systemName: "checkmark")
                    .font(.system(size: 8, weight: .bold))
                    .foregroundStyle(DS.white)
            }
        }
        .frame(width: 13, height: 13)
        .contentShape(Rectangle())
        .onTapGesture { isOn.toggle() }
    }
}

/// Toast Android (Toast.makeText) affiché en bas de l'écran.
struct AuthToast: View {
    let message: String

    var body: some View {
        Text(message)
            .font(.system(size: 14))
            .foregroundStyle(DS.white)
            .multilineTextAlignment(.center)
            .padding(.horizontal, 16)
            .padding(.vertical, 12)
            .background(DS.android(0x323232), in: Capsule())
            .padding(.horizontal, 24)
            .padding(.bottom, 64)
    }
}
