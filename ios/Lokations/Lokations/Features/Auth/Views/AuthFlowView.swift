import SwiftUI

/// Bascule connexion / inscription (équivalent login.kt + activity_register.kt).
struct AuthFlowView: View {
    enum Mode { case login, register }

    @EnvironmentObject private var session: SessionStore
    @State private var mode: Mode = .login
    @State private var email = ""
    @State private var password = ""
    @FocusState private var focused: AuthField?

    var body: some View {
        ZStack {
            AuthBackground(imageName: mode == .login ? "login_img1" : "login_img2")

            VStack(spacing: DS.Space.md) {
                Spacer()
                Text(mode == .login ? "Connexion" : "Inscription")
                    .font(.largeTitle.bold())
                    .foregroundStyle(DS.onDark)
                    .frame(maxWidth: .infinity, alignment: .leading)

                AuthTextField(field: .email, text: $email, focused: $focused)
                AuthTextField(field: .password, text: $password, focused: $focused)

                if let error = session.errorMessage {
                    Text(error)
                        .font(.footnote)
                        .foregroundStyle(DS.danger)
                        .frame(maxWidth: .infinity, alignment: .leading)
                }

                AuthPrimaryButton(title: mode == .login ? "Se connecter" : "S'inscrire",
                                  isLoading: session.isLoading, action: submit)

                Button(action: toggleMode) {
                    Text(mode == .login ? "Pas de compte ? **S'inscrire**" : "Déjà un compte ? **Se connecter**")
                        .foregroundStyle(DS.onDark)
                }
                .padding(.bottom, DS.Space.lg)
            }
            .padding(.horizontal, DS.Space.lg)
        }
        .onSubmit(of: .text) {
            if focused == .email { focused = .password } else { submit() }
        }
        .transition(.opacity)
    }

    private func submit() {
        focused = nil
        Task {
            switch mode {
            case .login: await session.signIn(email: email, password: password)
            case .register: await session.register(email: email, password: password)
            }
        }
    }

    private func toggleMode() {
        session.errorMessage = nil
        withAnimation { mode = mode == .login ? .register : .login }
    }
}
