import SwiftUI

/// Fiche ouverte par une ligne de `fragment_param.xml` (absente d'Android, R13 : pas de bouton
/// inerte). Style de l'écran : fond blanc, titre 14 gras, texte 13 black_70.
struct SettingsDetail: Identifiable {
    let title: String
    var id: String { title }
}

struct SettingsDetailSheet: View {
    @EnvironmentObject private var session: SessionStore
    @Environment(\.dismiss) private var dismiss
    let detail: SettingsDetail

    private var message: String {
        switch detail.title {
        case "Compte":
            return "Connecté en tant que \(session.email ?? "invité")\n"
                + (AppConfig.firebaseEnabled ? "Compte Firebase" : "Mode démo")
        case "Securité": return "Votre mot de passe est géré par votre compte Lokations."
        case "Confidentialité": return "Vos données restent privées et ne sont jamais revendues."
        case "Notification": return "Les notifications vous informent des nouvelles cités."
        case "Langue": return "Langue de l'application : Français"
        case "Vos Remarques", "Signaler Un Problème", "Assistance", "Contactez-Nous":
            return "Écrivez-nous depuis l'application Mail, nous vous répondrons rapidement."
        default: return "Conditions générales d'utilisation et politique de confidentialité de Lokations."
        }
    }

    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Spacer(minLength: 0)
                Button { dismiss() } label: {
                    Text("✕").font(.system(size: 16, weight: .bold)).foregroundStyle(DS.black)
                }
                .padding(.trailing, 25)
            }
            .frame(height: 50)
            Text(detail.title)
                .font(.system(size: 14, weight: .bold))
                .foregroundStyle(DS.black)
            Text(message)
                .font(.system(size: 13))
                .foregroundStyle(DS.blackA(70))
                .multilineTextAlignment(.center)
                .padding(.horizontal, 25)
                .padding(.top, 20)
            Spacer(minLength: 0)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(DS.white)
        .presentationDetents([.medium])
    }
}
