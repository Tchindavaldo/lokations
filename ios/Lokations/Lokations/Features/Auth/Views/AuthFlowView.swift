// Navigation login.kt <-> activity_register.kt (transition fade_in / fade_out).
// Écrans : AuthLoginView (activity_login.xml), AuthRegisterView (activity_register.xml).
import SwiftUI

struct AuthFlowView: View {
    enum Screen { case login, register }

    @EnvironmentObject private var session: SessionStore
    @State private var screen: Screen = .login

    var body: some View {
        ZStack(alignment: .bottom) {
            switch screen {
            case .login:
                AuthLoginView(onRegister: { go(.register) })
                    .transition(.opacity)
            case .register:
                AuthRegisterView(onLogin: { go(.login) })
                    .transition(.opacity)
            }
            if let error = session.errorMessage {
                AuthToast(message: error)
                    .transition(.opacity)
                    .task(id: error) {
                        try? await Task.sleep(for: .seconds(3.5))
                        if session.errorMessage == error { session.errorMessage = nil }
                    }
            }
        }
        .animation(.easeInOut(duration: 0.3), value: screen)
        .animation(.easeInOut(duration: 0.2), value: session.errorMessage)
        .transition(.opacity)
    }

    private func go(_ target: Screen) {
        session.errorMessage = nil
        screen = target
    }
}
