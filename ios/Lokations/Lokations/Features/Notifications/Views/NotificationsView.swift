import SwiftUI

/// Reproduit `res/layout/fragment_notif.xml` (fragment_notif.kt) : fond #FFFFFF,
/// titre "Notification" 14 gras centré (marge haute 20), puis ScrollView `container_notif`
/// (marges 40, 40 puis 45) qui apparaît en fondu sur 2 s (anim `fade_in_bottom_nav`).
/// Données : `MockData.notifications` ; "Consulter" ouvre la cité concernée.
struct NotificationsView: View {
    @State private var containerAlpha: Double = 1
    private let notifications = MockData.notifications

    var body: some View {
        NavigationStack {
            VStack(spacing: 0) {
                Text("Notification")
                    .font(.system(size: 14, weight: .bold))
                    .foregroundStyle(DS.black)
                    .padding(.top, 20)
                ScrollView {
                    VStack(spacing: 0) {
                        ForEach(Array(notifications.enumerated()), id: \.element.id) { index, notification in
                            NotifRow(notification: notification, cite: cite(for: notification))
                                .padding(.top, index < 2 ? 40 : 45)
                        }
                    }
                    .padding(.bottom, 20)
                    .opacity(containerAlpha)
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .background(DS.white)
            .toolbar(.hidden, for: .navigationBar)
            .navigationDestination(for: Cite.self) { CiteDetailView(cite: $0) }
            .onAppear {
                containerAlpha = 1
                
            }
        }
    }

    /// Cité citée par la notification (même image), sinon la première cité.
    private func cite(for notification: AppNotification) -> Cite? {
        MockData.allCites.first { $0.imageName == notification.imageName } ?? MockData.allCites.first
    }
}
