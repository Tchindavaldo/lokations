import Foundation
import FirebaseCore

/// Configuration centralisée (R8). Aucun secret en dur : Firebase lit GoogleService-Info.plist.
enum AppConfig {
    /// Vrai si GoogleService-Info.plist est présent et Firebase a été configuré.
    private(set) static var firebaseEnabled = false

    /// Collection / document Firestore partagés avec la version Android.
    enum Firestore {
        static let collection = "users"
        static let document = "user"
        static let listField = "listOfUsers"
    }

    static func configureFirebase() {
        guard Bundle.main.path(forResource: "GoogleService-Info", ofType: "plist") != nil else { return }
        FirebaseApp.configure()
        firebaseEnabled = true
    }
}
