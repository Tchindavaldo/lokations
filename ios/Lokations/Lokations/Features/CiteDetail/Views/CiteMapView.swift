import SwiftUI
import MapKit

// Page « localisation » (bouton plce de la barre du détail) : carte de la cité dans l'app,
// au lieu de renvoyer vers l'accueil comme sur Android.
struct CiteMapView: View {
    let info: CiteDetailInfo

    @Environment(\.openURL) private var openURL
    @State private var position: MapCameraPosition = .automatic
    @State private var coordinate: CLLocationCoordinate2D?

    var body: some View {
        ZStack(alignment: .top) {
            Map(position: $position) {
                if let coordinate {
                    Marker(info.cite.name, coordinate: coordinate)
                }
            }
            .ignoresSafeArea()

            VStack(alignment: .leading, spacing: 4) {
                Text(info.cite.name).font(.system(size: 17, weight: .bold)).lineLimit(1)
                Text(info.adresse).font(.system(size: 14)).foregroundStyle(DS.blackA(60)).lineLimit(1)
                Button("Ouvrir dans Plans") { if let url = info.mapsURL { openURL(url) } }
                    .font(.system(size: 14, weight: .semibold))
                    .padding(.top, 4)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(14)
            .background(.regularMaterial, in: RoundedRectangle(cornerRadius: 16))
            .padding(.horizontal, 16)
            .padding(.top, 8)
        }
        .task { await locate() }
    }

    private func locate() async {
        let query = "\(info.adresse), Cameroun"
        guard let place = try? await CLGeocoder().geocodeAddressString(query).first,
              let loc = place.location else { return }
        coordinate = loc.coordinate
        position = .region(MKCoordinateRegion(center: loc.coordinate,
                                               latitudinalMeters: 1500, longitudinalMeters: 1500))
    }
}
