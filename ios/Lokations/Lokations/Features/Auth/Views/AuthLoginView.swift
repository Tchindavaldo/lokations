// Reproduit activity_login.xml (login.kt) : fond mblur3 + voile #B3000000,
// colonne marges 25dp : en-tête (poids 0.30), formulaire (wrap, marginTop 20dp),
// pied réseaux sociaux / lien inscription (poids 0.3, paddingBottom 20dp).
import SwiftUI

struct AuthLoginView: View {
    let onRegister: () -> Void

    @EnvironmentObject private var session: SessionStore
    @State private var email = ""
    @State private var password = ""
    @State private var remember = false

    private let white50 = DS.android(0x80FFFFFF, hasAlpha: true)

    var body: some View {
        ZStack {
            AuthBackground()
            AuthWeightedColumn {
                header.authWeight(0.30)
                form.padding(.top, 20)
                footer.authWeight(0.3)
            }
            .padding(.horizontal, 25)
        }
    }

    private var header: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Bienvenue sur \nLokations ")
                .font(.system(size: 30, weight: .bold))
                .foregroundStyle(DS.white)
            Text("Trouver la maison la chambre l'appartement le studio ou l'hotel qui vous convient")
                .font(.system(size: 20))
                .foregroundStyle(DS.white)
                .padding(.top, 15)
        }
        .padding(.top, 10)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
    }

    private var form: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Connecter vous pour continuer")
                .font(.system(size: 13, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(.bottom, 5)
            AuthInputField(icon: "ic_baseline_person_outline_24", placeholder: "Email",
                           text: $email, keyboard: .emailAddress)
            AuthInputField(icon: "ic_baseline_lock_24", placeholder: "..........",
                           text: $password, textSize: 25, isPassword: true)
                .padding(.top, 30)
            HStack(spacing: 0) {
                Text("se souvenoir la prochaine fois")
                    .font(.system(size: 11))
                    .foregroundStyle(white50)
                Spacer(minLength: 0)
                AuthMaterialSwitch(isOn: $remember)
            }
            .padding(.top, 10)
            VStack(spacing: 0) {
                AuthBlackButton(title: "connnexion", action: submit)
                Text("mot de pase oublié? ")
                    .font(.system(size: 15))
                    .foregroundStyle(white50)
                    .padding(.top, 5)
            }
            .frame(maxWidth: .infinity)
            .padding(.top, 35)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    private var footer: some View {
        AuthWeightedColumn {
            AuthOrDivider()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .padding(.top, 25)
                .authWeight(0.5, margin: 25)
            Text("connexion via resau social ")
                .font(.system(size: 15))
                .foregroundStyle(white50)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .authWeight(0.3)
            AuthSocialRow()
                .frame(maxWidth: .infinity, maxHeight: .infinity)
                .authWeight(0.3)
            HStack(spacing: 0) {
                Text("vous n'avez pas de compte? ")
                    .font(.system(size: 15))
                    .foregroundStyle(white50)
                Text("Inscription ")
                    .font(.system(size: 16, weight: .bold))
                    .foregroundStyle(DS.white)
                    .onTapGesture(perform: onRegister)
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .authWeight(0.3)
        }
        .padding(.bottom, 20)
    }

    private func submit() {
        Task { await session.signIn(email: email, password: password) }
    }
}
