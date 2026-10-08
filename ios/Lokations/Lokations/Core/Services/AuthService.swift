import Foundation
import FirebaseAuth

/// Accès Firebase Auth pur (sans état). Le state vit dans SessionStore.
protocol AuthServicing {
    func signIn(email: String, password: String) async throws -> String
    func register(email: String, password: String) async throws -> String
    func signOut() throws
    var currentEmail: String? { get }
}

struct FirebaseAuthService: AuthServicing {
    func signIn(email: String, password: String) async throws -> String {
        let result = try await Auth.auth().signIn(withEmail: email, password: password)
        return result.user.email ?? email
    }

    func register(email: String, password: String) async throws -> String {
        let result = try await Auth.auth().createUser(withEmail: email, password: password)
        return result.user.email ?? email
    }

    func signOut() throws { try Auth.auth().signOut() }

    var currentEmail: String? { Auth.auth().currentUser?.email }
}

/// Mode démo quand GoogleService-Info.plist est absent.
struct DemoAuthService: AuthServicing {
    func signIn(email: String, password: String) async throws -> String { email }
    func register(email: String, password: String) async throws -> String { email }
    func signOut() throws {}
    var currentEmail: String? { nil }
}
