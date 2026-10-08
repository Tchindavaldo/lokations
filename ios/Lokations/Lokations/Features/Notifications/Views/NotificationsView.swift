import SwiftUI

/// Notifications (équivalent fragment_notif).
struct NotificationsView: View {
    private let notifications = MockData.notifications

    var body: some View {
        NavigationStack {
            List(notifications) { notification in
                NotificationRow(notification: notification)
            }
            .listStyle(.plain)
            .navigationTitle("Notifications")
        }
    }
}

struct NotificationRow: View {
    let notification: AppNotification

    var body: some View {
        HStack(spacing: DS.Space.md) {
            Image(notification.imageName).resizable().scaledToFill()
                .frame(width: 60, height: 60)
                .clipShape(Circle())
            VStack(alignment: .leading, spacing: DS.Space.xs) {
                Text(notification.title).font(.headline)
                Text(notification.subtitle).font(.caption).foregroundStyle(DS.textSecondary)
                Text(notification.detail).font(.caption).foregroundStyle(DS.textSecondary)
            }
            Spacer(minLength: 0)
            Text("Consulter").font(.subheadline.bold())
        }
        .padding(.vertical, DS.Space.xs)
    }
}
