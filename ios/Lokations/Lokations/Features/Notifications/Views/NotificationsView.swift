import SwiftUI

/// Reproduit `res/layout/fragment_notif.xml` (fragment_notif.kt) : fond #FFFFFF,
/// titre "Notification" 14 gras centré (marge haute 20), puis ScrollView `container_notif`
/// de 8 lignes, qui apparaît en fondu sur 2 s (anim `fade_in_bottom_nav`).
struct NotificationsView: View {
    @State private var containerAlpha: Double = 0

    /// Lignes du XML, dans l'ordre : image (ou icône sac) et marge haute.
    private let rows: [NotifRowData] = [
        .init(imageName: "m6", marginTop: 40),
        .init(imageName: "m7", marginTop: 40),
        .init(imageName: "m4", marginTop: 45),
        .init(imageName: "m7", marginTop: 45),
        .init(imageName: "m6", marginTop: 45),
        .init(imageName: "m4", marginTop: 45),
        .init(imageName: "m88", marginTop: 45),
        .init(imageName: nil, marginTop: 45),
    ]

    var body: some View {
        VStack(spacing: 0) {
            Text("Notification")
                .font(.system(size: 14, weight: .bold))
                .foregroundStyle(DS.black)
                .multilineTextAlignment(.center)
                .padding(.top, 20)
            ScrollView {
                VStack(spacing: 0) {
                    ForEach(rows) { row in
                        NotifRow(imageName: row.imageName)
                            .padding(.top, row.marginTop)
                    }
                }
                .opacity(containerAlpha)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .background(DS.white)
        .onAppear {
            containerAlpha = 0
            withAnimation(.easeInOut(duration: 2)) { containerAlpha = 1 }
        }
    }
}

struct NotifRowData: Identifiable {
    let id = UUID()
    let imageName: String?
    let marginTop: CGFloat
}
