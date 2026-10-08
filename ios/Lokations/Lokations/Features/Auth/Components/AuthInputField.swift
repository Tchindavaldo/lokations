// Champ de saisie des layouts activity_login.xml / activity_register.xml :
// LinearLayout 45dp, fond @drawable/round_black_25_15 (#40000000, rayon 15dp),
// icône 14x16dp dans une colonne de poids 0.2, EditText de poids 1
// (texte white_50, bold), icône de visibilité optionnelle (poids 0.2).
import SwiftUI

struct AuthInputField: View {
    let icon: String
    let placeholder: String
    @Binding var text: String
    var textSize: CGFloat = 15
    var isPassword = false
    var keyboard: UIKeyboardType = .default

    @State private var revealed = false

    var body: some View {
        GeometryReader { geo in
            let totalWeight: CGFloat = isPassword ? 1.4 : 1.2
            let iconWidth = geo.size.width * 0.2 / totalWeight
            HStack(spacing: 0) {
                iconColumn(icon).frame(width: iconWidth)
                input
                    .padding(.horizontal, 4)
                    .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
                if isPassword {
                    Button { revealed.toggle() } label: {
                        iconColumn("ic_baseline_visibility_24")
                    }
                    .buttonStyle(.plain)
                    .frame(width: iconWidth)
                }
            }
        }
        .frame(height: 45)
        .frame(maxWidth: .infinity)
        .background(DS.android(0x40000000, hasAlpha: true),
                    in: RoundedRectangle(cornerRadius: 15))
    }

    private func iconColumn(_ name: String) -> some View {
        Image(name)
            .resizable()
            .frame(width: 14, height: 16)
            .frame(maxWidth: .infinity, maxHeight: .infinity)
    }

    @ViewBuilder
    private var input: some View {
        let prompt = Text(placeholder)
            .font(.system(size: textSize, weight: .bold))
            .foregroundColor(DS.android(0x80FFFFFF, hasAlpha: true))
        Group {
            if isPassword && !revealed {
                SecureField("", text: $text, prompt: prompt)
                    .textContentType(.password)
            } else {
                TextField("", text: $text, prompt: prompt)
                    .keyboardType(keyboard)
                    .textInputAutocapitalization(keyboard == .emailAddress ? .never : .sentences)
                    .autocorrectionDisabled()
            }
        }
        .font(.system(size: textSize, weight: .bold))
        .foregroundStyle(DS.android(0x80FFFFFF, hasAlpha: true))
        .tint(DS.white)
    }
}
