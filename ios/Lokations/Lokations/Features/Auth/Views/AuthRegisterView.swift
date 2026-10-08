// Reproduit activity_register.xml (activity_register.kt) : fond mblur3 + voile
// #B3000000, colonne marges 25dp : en-tête (poids 0.20), formulaire (wrap),
// pied réseaux sociaux / lien connexion (poids 0.3, paddingBottom 20dp).
import SwiftUI

struct AuthRegisterView: View {
    let onLogin: () -> Void

    @EnvironmentObject private var session: SessionStore
    @State private var lastName = ""
    @State private var firstName = ""
    @State private var email = ""
    @State private var phone = ""
    @State private var password = ""
    @State private var accepted = false

    private let white50 = DS.android(0x80FFFFFF, hasAlpha: true)

    var body: some View {
        ZStack {
            AuthBackground()
            AuthWeightedColumn {
                header.authWeight(0.20)
                form
                footer.authWeight(0.3)
            }
            .padding(.horizontal, 25)
        }
    }

    private var header: some View {
        Text("Bienvenue sur \nLokations ")
            .font(.system(size: 30, weight: .bold))
            .foregroundStyle(DS.white)
            .padding(.top, 10)
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
    }

    private var form: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Inscriez vous pour continuer")
                .font(.system(size: 13, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(.bottom, 5)
            AuthInputField(icon: "ic_baseline_person_outline_24", placeholder: "Nom",
                           text: $lastName)
            AuthInputField(icon: "ic_baseline_person_outline_24", placeholder: "Prenom",
                           text: $firstName)
                .padding(.top, 20)
            AuthInputField(icon: "ic_baseline_person_outline_24", placeholder: "Email",
                           text: $email, keyboard: .emailAddress)
                .padding(.top, 20)
            AuthInputField(icon: "ic_baseline_call2_24", placeholder: "Numero Tel",
                           text: $phone, keyboard: .phonePad)
                .padding(.top, 20)
            AuthInputField(icon: "ic_baseline_lock_24", placeholder: "Mot de pase",
                           text: $password, isPassword: true)
                .padding(.top, 20)
            HStack(alignment: .bottom, spacing: 0) {
                Text("En vous inscrivant vous acceptez nos conditions d'utilisation, notre politique de confidentialité et notre utilisation des cookies.")
                    .font(.system(size: 10))
                    .foregroundStyle(white50)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.trailing, 15)
                AuthCheckBox(isOn: $accepted)
            }
            .padding(.top, 10)
            AuthBlackButton(title: "inscription", action: submit)
                .padding(.top, 35)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    private var footer: some View {
        AuthWeightedColumn {
            AuthOrDivider()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .padding(.top, 10)
                .authWeight(0.3, margin: 10)
            Text("inscription via resau social ")
                .font(.system(size: 15))
                .foregroundStyle(white50)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .authWeight(0.40)
            AuthSocialRow()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .padding(.vertical, 5)
                .authWeight(0.3, margin: 10)
            HStack(spacing: 0) {
                Text("vous avez déjà un compte?")
                    .font(.system(size: 15))
                    .foregroundStyle(white50)
                Text("connexion ")
                    .font(.system(size: 16, weight: .bold))
                    .foregroundStyle(DS.white)
                    .onTapGesture(perform: onLogin)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .authWeight(0.35)
        }
        .padding(.bottom, 20)
    }

    private func submit() {
        Task { await session.register(email: email, password: password) }
    }
}
