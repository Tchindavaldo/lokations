import SwiftUI

/// Paramètres / profil (équivalent fragment_param).
struct SettingsView: View {
    @EnvironmentObject private var session: SessionStore
    @EnvironmentObject private var favorites: FavoritesStore
    @AppStorage("settings.notifications") private var notificationsOn = true
    @State private var confirmSignOut = false

    var body: some View {
        NavigationStack {
            Form {
                Section {
                    HStack(spacing: DS.Space.md) {
                        Image(systemName: "person.crop.circle.fill")
                            .font(.system(size: 52))
                            .foregroundStyle(DS.ink)
                        VStack(alignment: .leading) {
                            Text(session.email ?? "Invité").font(.headline)
                            Text(AppConfig.firebaseEnabled ? "Compte Firebase" : "Mode démo")
                                .font(.caption)
                                .foregroundStyle(DS.textSecondary)
                        }
                    }
                }
                Section("Préférences") {
                    Toggle("Notifications", isOn: $notificationsOn)
                    LabeledContent("Favoris", value: "\(favorites.names.count)")
                }
                Section("À propos") {
                    LabeledContent("Version", value: Bundle.main.appVersion)
                }
                Section {
                    Button("Se déconnecter", role: .destructive) { confirmSignOut = true }
                }
            }
            .navigationTitle("Paramètres")
            .confirmationDialog("Se déconnecter ?", isPresented: $confirmSignOut, titleVisibility: .visible) {
                Button("Se déconnecter", role: .destructive) { session.signOut() }
            }
        }
    }
}

private extension Bundle {
    var appVersion: String {
        (infoDictionary?["CFBundleShortVersionString"] as? String) ?? "1.0"
    }
}
