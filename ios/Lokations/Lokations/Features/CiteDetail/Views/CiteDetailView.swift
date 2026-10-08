import SwiftUI

// Reproduit res/layout/frame_layout.xml (FrameLayoutActivity) :
// fragment courant plein écran + barre verticale droite (container_item_nav)
// + barre horizontale basse (container_item_nav2). Les translations Android sont en px,
// converties en pt pour un écran @3x (98px -> 32.7pt, 102px -> 34pt, -2px -> -0.7pt).
// Paiement : `.sheet` natif (R13). Localisation : ouvre Plans sur l'adresse de la cité
// (Android rouvrait HomeFragment). Favori : FavoritesStore, en barre de navigation.

enum CiteDetailPage {
    case detail, photo, payment, comment, localisation
}

/// Données passées en extras d'intent (categori, lieux, ItemCategorie, prix).
struct CiteDetailInfo {
    let cite: Cite
    let categorie: String
    let itemCategorie: String
    let lieux: String
    let prix: String

    /// Numéro de contact de démonstration (le modèle Cite n'en porte pas encore).
    static let contactPhone = "+237696080087"

    var gallery: [String] { cite.gallery }
    var adresse: String { "\(cite.district), \(cite.city)" }
    var mensuel: String { "\(Self.grouped(cite.pricePerMonth)) FCFA / mois" }
    var telURL: URL? { URL(string: "tel:\(Self.contactPhone)") }
    var mapsURL: URL? {
        var c = URLComponents(string: "http://maps.apple.com/")
        c?.queryItems = [URLQueryItem(name: "q", value: "\(cite.name), \(adresse)")]
        return c?.url
    }

    init(cite: Cite) {
        self.cite = cite
        categorie = cite.name
        itemCategorie = "\(cite.totalRooms) chambres"
        lieux = "\(cite.city), \(cite.district)"
        prix = "\(Self.grouped(cite.pricePerMonth)) /mois"
    }

    static func grouped(_ value: Int) -> String {
        let f = NumberFormatter()
        f.numberStyle = .decimal
        f.groupingSeparator = " "
        f.groupingSize = 3
        return f.string(from: NSNumber(value: value)) ?? "\(value)"
    }
}

struct CiteDetailView: View {
    let cite: Cite

    @Environment(\.openURL) private var openURL
    @EnvironmentObject private var favorites: FavoritesStore
    @State private var page: CiteDetailPage = .photo
    @State private var showPayment = false

    private var info: CiteDetailInfo { CiteDetailInfo(cite: cite) }

    var body: some View {
        ZStack(alignment: .bottom) {
            DS.white.ignoresSafeArea()

            Group {
                switch page {
                case .detail: CiteDetailInfoView(info: info) { showPayment = true }
                case .comment: CiteCommentView(info: info)
                default: CitePhotoView(info: info)
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)

            // Barre unique dédiée au détail (frame_layout.xml : menu détail), toujours visible.
            CiteDetailBottomBar(selected: page, onTap: tap)
                .padding(.bottom, 8)
        }
        .transaction { $0.animation = nil }
        .toolbar(.hidden, for: .tabBar)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button { favorites.toggle(cite) } label: {
                    Image(systemName: favorites.contains(cite) ? "heart.fill" : "heart")
                        .foregroundStyle(DS.favorite)
                }
                .accessibilityLabel("Favori")
            }
        }
        .sheet(isPresented: $showPayment) {
            PaymentView(cite: cite)
                .presentationDetents([.large])
        }
    }

    /// Infos / photos / avis changent la page sur place ; paiement ouvre la feuille ;
    /// localisation ouvre Plans. Aucun retour vers l'accueil.
    private func tap(_ item: CiteDetailPage) {
        switch item {
        case .payment: showPayment = true
        case .localisation: if let url = info.mapsURL { openURL(url) }
        default: page = item
        }
    }
}
