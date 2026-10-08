import SwiftUI

// Reproduit : res/layout/fragmentbtiqueboutique.xml, cartes @id/ajout_produit,
// @id/modifier_produit… (120x80, @drawable/round_e6e0e3_10, pastille @drawable/round_white_50
// avec @drawable/ic_baseline_add_box_24 15dp).
struct BoutiqueShopActionCard: View {
    let top: String
    let bottom: String
    var topColor: Color = DS.blackA(50)
    var bottomColor: Color = DS.black
    var action: (() -> Void)?

    init(top: String, bottom: String, topColor: Color = DS.blackA(50),
         bottomColor: Color = DS.black, action: (() -> Void)? = nil) {
        self.top = top
        self.bottom = bottom
        self.topColor = topColor
        self.bottomColor = bottomColor
        self.action = action
    }

    var body: some View {
        content
            .contentShape(Rectangle())
            .onTapGesture { action?() }
    }

    private var content: some View {
        VStack(alignment: .leading, spacing: 0) {
            Image("ic_baseline_add_box_24")
                .resizable()
                .frame(width: 15, height: 15)
                .padding(5)
                .background(Circle().fill(DS.white))
            Spacer(minLength: 0)
            Text(top)
                .font(.system(size: 12))
                .foregroundStyle(topColor)
                .padding(.leading, 5)
                .padding(.top, 10)
            Spacer(minLength: 0)
            Text(bottom)
                .font(.system(size: 12))
                .foregroundStyle(bottomColor)
                .padding(.leading, 5)
                .padding(.bottom, 10)
        }
        .padding(.leading, 10)
        .padding(.top, 10)
        .frame(width: 120, height: 80, alignment: .topLeading)
        .background(RoundedRectangle(cornerRadius: 10).fill(DS.android(0xE6E0E3)))
    }
}
