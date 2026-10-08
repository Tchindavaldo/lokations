import SwiftUI

// Maquette « B · Photo immersive — Détail » (remplace la reproduction de frame_layout.xml) :
// galerie plein écran, dégradé sombre, boutons verre retour / favori, bloc titre +
// puces (Photos, Avis, Carte, Appeler) et capsule prix + « Réserver » (PaymentView).

struct CiteDetailView: View {
    let cite: Cite

    @Environment(\.dismiss) private var dismiss
    @Environment(\.openURL) private var openURL
    @EnvironmentObject private var favorites: FavoritesStore
    @State private var page = 0
    @State private var showPhotos = false
    @State private var showAvis = false
    @State private var showPayment = false

    private var info: CiteDetailInfo { CiteDetailInfo(cite: cite) }

    var body: some View {
        ZStack {
            DS.black.ignoresSafeArea()

            TabView(selection: $page) {
                ForEach(cite.gallery.indices, id: \.self) { i in
                    Image(cite.gallery[i])
                        .resizable()
                        .scaledToFill()
                        .containerRelativeFrame([.horizontal, .vertical])
                        .clipped()
                        .tag(i)
                }
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .ignoresSafeArea()

            LinearGradient(stops: [
                .init(color: DS.blackA(35), location: 0),
                .init(color: DS.blackA(0), location: 0.25),
                .init(color: DS.blackA(0), location: 0.40),
                .init(color: DS.blackA(88), location: 0.75),
                .init(color: DS.blackA(88), location: 1),
            ], startPoint: .top, endPoint: .bottom)
            .ignoresSafeArea()
            .allowsHitTesting(false)

            VStack(spacing: 0) {
                topBar
                Spacer(minLength: 0)
                details
                    .padding(.horizontal, 16)
                    .padding(.bottom, 120 - 34)
            }

            priceBar
                .padding(.horizontal, 16)
                .padding(.bottom, 34)
                .frame(maxHeight: .infinity, alignment: .bottom)
                .ignoresSafeArea(edges: .bottom)
        }
        .toolbar(.hidden, for: .navigationBar)
        .toolbar(.hidden, for: .tabBar)
        .sheet(isPresented: $showPhotos) {
            CiteDetailPhotoViewer(images: cite.gallery, index: page)
        }
        .sheet(isPresented: $showAvis) {
            CiteCommentView(info: info).presentationDetents([.large])
        }
        .sheet(isPresented: $showPayment) {
            PaymentView(cite: cite).presentationDetents([.large])
        }
    }

    // MARK: Haut

    private var topBar: some View {
        HStack {
            CiteDetailCircleButton(systemName: "chevron.left", label: "Retour") { dismiss() }
            Spacer()
            CiteDetailCircleButton(systemName: favorites.contains(cite) ? "heart.fill" : "heart",
                                   tint: favorites.contains(cite) ? DS.favorite : DS.white,
                                   label: "Favori") { favorites.toggle(cite) }
        }
        .padding(.horizontal, 16)
        .padding(.top, 8)
    }

    // MARK: Bloc bas

    private var details: some View {
        VStack(alignment: .leading, spacing: 10) {
            HStack(spacing: 6) {
                ForEach(cite.gallery.indices, id: \.self) { i in
                    Capsule()
                        .fill(i == page ? DS.white : DS.whiteA(50))
                        .frame(width: i == page ? 20 : 8, height: 4)
                }
            }
            .animation(.easeInOut(duration: 0.2), value: page)

            Text(cite.name)
                .font(.system(size: 32, weight: .bold))
                .foregroundStyle(DS.white)
                .lineLimit(2)
                .minimumScaleFactor(0.8)

            Text("\(info.adresse) · \(cite.totalRooms) chambres · \(cite.freeRooms) libres")
                .font(.system(size: 15))
                .foregroundStyle(DS.whiteA(85))
                .lineLimit(1)
                .truncationMode(.tail)

            Text(cite.summary)
                .font(.system(size: 15))
                .foregroundStyle(DS.whiteA(90))
                .lineLimit(3)

            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 8) {
                    Button { showPhotos = true } label: {
                        CiteDetailChipLabel(systemName: "photo.on.rectangle", title: "Photos")
                    }
                    Button { showAvis = true } label: {
                        CiteDetailChipLabel(systemName: "star.fill", title: "Avis 4,5")
                    }
                    Button { if let url = info.mapsURL { openURL(url) } } label: {
                        CiteDetailChipLabel(systemName: "map", title: "Carte")
                    }
                    Button { if let url = info.telURL { openURL(url) } } label: {
                        CiteDetailChipLabel(systemName: "phone.fill", title: "Appeler")
                    }
                }
                .buttonStyle(.plain)
            }
            .padding(.top, 6)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    // MARK: Capsule prix

    private var priceBar: some View {
        HStack(spacing: 12) {
            (Text("\(CiteDetailInfo.grouped(cite.pricePerMonth)) F")
                .font(.system(size: 20, weight: .bold))
                .foregroundColor(DS.white)
             + Text(" / mois")
                .font(.system(size: 14))
                .foregroundColor(DS.whiteA(80)))
                .lineLimit(1)
                .minimumScaleFactor(0.7)
            Spacer(minLength: 0)
            Button { showPayment = true } label: {
                Text("Réserver")
                    .font(.system(size: 17, weight: .semibold))
                    .foregroundStyle(DS.black)
                    .padding(.horizontal, 24)
                    .frame(height: 48)
                    .background(DS.white, in: Capsule())
            }
            .buttonStyle(.plain)
        }
        .padding(.leading, 20)
        .padding(.trailing, 8)
        .frame(height: 64)
        .citeDetailGlass(Capsule())
    }
}
