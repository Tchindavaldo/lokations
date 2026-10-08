import SwiftUI

/// Détail d'une cité (équivalent FrameLayoutActivity + fragments photo/info/comment/stat).
struct CiteDetailView: View {
    let cite: Cite

    enum Tab: String, CaseIterable, Identifiable {
        case info = "Infos", rooms = "Chambres", reviews = "Avis", stats = "Stats"
        var id: String { rawValue }
    }

    @EnvironmentObject private var favorites: FavoritesStore
    @State private var tab: Tab = .info
    @State private var showPayment = false

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: DS.Space.md) {
                CiteGallery(images: cite.gallery)
                CiteHeader(cite: cite)
                    .padding(.horizontal, DS.Space.md)

                Picker("Section", selection: $tab) {
                    ForEach(Tab.allCases) { Text($0.rawValue).tag($0) }
                }
                .pickerStyle(.segmented)
                .padding(.horizontal, DS.Space.md)

                Group {
                    switch tab {
                    case .info: CiteInfoTab(cite: cite)
                    case .rooms: CiteRoomsTab(cite: cite)
                    case .reviews: CiteReviewsTab()
                    case .stats: CiteStatsTab()
                    }
                }
                .padding(.horizontal, DS.Space.md)
                .animation(.easeInOut, value: tab)
            }
            .padding(.bottom, DS.Space.lg)
        }
        .navigationTitle(cite.name)
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
        .safeAreaInset(edge: .bottom) {
            Button { showPayment = true } label: {
                Text("Réserver - \(cite.pricePerMonth.formatted()) FCFA / mois")
                    .fontWeight(.semibold)
                    .frame(maxWidth: .infinity)
                    .padding(DS.Space.md)
                    .background(DS.ink, in: RoundedRectangle(cornerRadius: DS.Radius.md))
                    .foregroundStyle(DS.onDark)
            }
            .padding(.horizontal, DS.Space.md)
            .padding(.top, DS.Space.sm)
            .background(.bar)
        }
        .sheet(isPresented: $showPayment) {
            PaymentSheet(cite: cite)
                .presentationDetents([.medium, .large])
        }
    }
}
