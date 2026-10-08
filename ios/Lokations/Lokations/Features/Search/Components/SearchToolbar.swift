import SwiftUI

/// Reproduit la Toolbar de `res/layout/fragment_search.xml` :
/// fond #0025121F, pastille `@drawable/round` 155x35 (#4DB18484, rayon 27) avec EditText 135
/// et icône `search` 20 ; à droite `pps` 20x30, `notif` 20x30 (marges 8), `ccount2` 35x35 (marge 20).
struct SearchToolbar: View {
    @Binding var query: String

    var body: some View {
        HStack(spacing: 0) {
            HStack(spacing: 0) {
                TextField("", text: $query)
                    .font(.system(size: 18))
                    .foregroundStyle(DS.black)
                    .padding(.horizontal, 4)
                    .frame(width: 135, height: 35)
                Image("search")
                    .renderingMode(.template)
                    .resizable()
                    .scaledToFit()
                    .foregroundStyle(DS.android(0x000000))
                    .frame(width: 20, height: 35)
                Spacer(minLength: 0)
            }
            .frame(width: 155, height: 35)
            .background(RoundedRectangle(cornerRadius: 27).fill(DS.android(0x4DB18484, hasAlpha: true)))

            Spacer(minLength: 0)

            Image("pps")
                .renderingMode(.template)
                .resizable()
                .scaledToFit()
                .foregroundStyle(DS.android(0x9FDB9D))
                .frame(width: 20, height: 30)
                .padding(.trailing, 8)
            Image("notif")
                .renderingMode(.template)
                .resizable()
                .scaledToFit()
                .foregroundStyle(DS.android(0x25121F))
                .frame(width: 20, height: 30)
                .padding(.trailing, 8)
            Image("ccount2")
                .renderingMode(.template)
                .resizable()
                .scaledToFit()
                .foregroundStyle(DS.android(0x25121F))
                .frame(width: 35, height: 35)
                .padding(.trailing, 20)
        }
        .padding(.leading, 16) // contentInsetStart de la Toolbar AppCompat
        .frame(maxWidth: .infinity, minHeight: 56) // ?attr/actionBarSize
        .background(DS.android(0x0025121F, hasAlpha: true))
    }
}
