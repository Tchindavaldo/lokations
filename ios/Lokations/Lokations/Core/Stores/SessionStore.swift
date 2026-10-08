import Foundation

/// Source de vérité de la session utilisateur (équivalent AuthContext, R6).
@MainActor
final class SessionStore: ObservableObject {
    @Published private(set) var email: String?
    @Published private(set) var isLoading = false
    @Published var errorMessage: String?
    /// Prénom + nom saisis à l'inscription (activity_register.xml).
    @Published private(set) var displayName: String?

    var isLoggedIn: Bool { email != nil }

    private let service: AuthServicing

    init(service: AuthServicing) {
        self.service = service
        email = service.currentEmail
    }

    func signIn(email: String, password: String) async {
        await run(email: email, password: password) { try await self.service.signIn(email: $0, password: $1) }
    }

    func register(email: String, password: String) async {
        await run(email: email, password: password) { try await self.service.register(email: $0, password: $1) }
    }

    func register(email: String, password: String, firstName: String, lastName: String) async {
        await register(email: email, password: password)
        if isLoggedIn {
            let name = "\(firstName) \(lastName)".trimmingCharacters(in: .whitespaces)
            displayName = name.isEmpty ? nil : name
        }
    }

    func signOut() {
        do {
            try service.signOut()
            email = nil
            displayName = nil
        } catch {
            errorMessage = error.localizedDescription
        }
    }

    private func run(email: String, password: String,
                     _ action: (String, String) async throws -> String) async {
        let email = email.trimmingCharacters(in: .whitespaces)
        guard !email.isEmpty, !password.isEmpty else {
            errorMessage = "Veuillez saisir votre email et votre mot de passe."
            return
        }
        errorMessage = nil
        isLoading = true
        defer { isLoading = false }
        do {
            self.email = try await action(email, password)
        } catch {
            errorMessage = error.localizedDescription
        }
    }
}
