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
    /// Fond sélectionné de la barre basse (btn_*2) ; photo2 sélectionné dans le XML.
    @State private var selected2: CiteDetailPage = .photo
    @State private var rightOffset: CGFloat = 98.0 / 3
    @State private var bottomOffset: CGFloat = 102.0 / 3
    @State private var showPayment = false

    private var info: CiteDetailInfo { CiteDetailInfo(cite: cite) }

    var body: some View {
        ZStack {
            DS.white.ignoresSafeArea()

            Group {
                switch page {
                case .detail: CiteDetailInfoView(info: info) { showPayment = true }
                case .comment: CiteCommentView(info: info)
                default: CitePhotoView(info: info)
                }
            }

            CiteDetailRightBar(onTap: tapRight)
                .padding(.bottom, 35)
                .offset(x: rightOffset)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .bottomTrailing)

            CiteDetailBottomBar(selected: selected2, onTap: tapBottom)
                .padding(.bottom, 5)
                .offset(y: bottomOffset)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .bottom)
        }
        .toolbar(.hidden, for: .tabBar)
        .toolbarBackground(.hidden, for: .navigationBar)
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
        .onAppear { showRightBar() }
        .sheet(isPresented: $showPayment) {
            PaymentView(cite: cite)
                .presentationDetents([.large])
        }
    }

    // MARK: - Clics (ontlis de FrameLayoutActivity)

    private func tapRight(_ item: CiteDetailPage) {
        // Paiement (feuille) et localisation (Plans) n'altèrent pas l'écran courant.
        if item == .payment || item == .localisation { open(item); return }
        selected2 = item
        guard item != .photo else { return } // btn_photo : typeTransac "f1", pas de remplacement
        open(item)
        withAnimation(.easeInOut(duration: 0.8)) { rightOffset = 98.0 / 3 }
        withAnimation(.easeInOut(duration: 0.8).delay(0.4)) { bottomOffset = 0 }
    }

    private func tapBottom(_ item: CiteDetailPage) {
        // Paiement (feuille) et localisation (Plans) n'altèrent pas l'écran courant.
        if item == .payment || item == .localisation { open(item); return }
        selected2 = item
        open(item)
        if item == .photo {
            showRightBar()
            withAnimation(.easeInOut(duration: 0.8)) { bottomOffset = 102.0 / 3 }
        }
    }

    private func open(_ item: CiteDetailPage) {
        switch item {
        case .payment: showPayment = true
        case .localisation: if let url = info.mapsURL { openURL(url) }
        default: page = item
        }
    }

    private func showRightBar() {
        withAnimation(.easeInOut(duration: 0.8).delay(0.4)) { rightOffset = -2.0 / 3 }
    }
}
