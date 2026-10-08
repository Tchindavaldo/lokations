import SwiftUI

/// Reproduit une ligne de `res/layout/fragment_notif.xml` : paddingLeft 15 / paddingRight 25,
/// CardView ronde 55x55 (image centerCrop, fond #80FFFFFF), textes 12 gras / 10 black_50
/// (agrandis à 12), "Consulter" 14 gras qui ouvre la cité.
struct NotifRow: View {
    let notification: AppNotification
    let cite: Cite?

    var body: some View {
        HStack(alignment: .top, spacing: 0) {
            Image(notification.imageName)
                .resizable()
                .scaledToFill()
                .frame(width: 55, height: 55)
                .background(DS.android(0x80FFFFFF, hasAlpha: true))
                .clipShape(Circle())
            VStack(alignment: .leading, spacing: 0) {
                Text(notification.title)
                    .font(.system(size: 13, weight: .bold))
                    .foregroundStyle(DS.black)
                Spacer(minLength: 0)
                Text(notification.subtitle)
                    .font(.system(size: 12))
                    .foregroundStyle(DS.blackA(50))
                Text(notification.detail)
                    .font(.system(size: 12))
                    .foregroundStyle(DS.blackA(50))
                    .fixedSize(horizontal: false, vertical: true)
            }
            .padding(.leading, 15)
            .padding(.bottom, 2)
            .frame(maxWidth: .infinity, minHeight: 55, alignment: .topLeading)
            if let cite {
                NavigationLink(value: cite) {
                    Text("Consulter")
                        .font(.system(size: 14, weight: .bold))
                        .foregroundStyle(DS.black)
                }
                .buttonStyle(.plain)
            }
        }
        .padding(.leading, 15)
        .padding(.trailing, 25)
    }
}
