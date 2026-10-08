import SwiftUI

// Reproduit res/layout/fragment_photo.xml (fragment_photo.kt) : pager plein écran
// (galerie de la cité au lieu des login_img1..6 de test), bloc haut (categorie + emplacement), bloc bas (Itemcategorie, prix,
// label_Itemcategorie) et pastille de l'indicateur. Les translations d'entrée (px)
// et leurs délais (onResume) sont reproduits ; px convertis pour un écran @3x.

struct CitePhotoView: View {
    let info: CiteDetailInfo

    @State private var catY: CGFloat = 0
    @State private var emplX: CGFloat = 0
    @State private var itemX: CGFloat = 0
    @State private var prixX: CGFloat = 0
    @State private var labelY: CGFloat = 0

    var body: some View {
        ZStack(alignment: .topLeading) {
            CitePhotoPager(images: info.gallery)
                .ignoresSafeArea()

            topDetail

            bottomDetail
                .padding(.bottom, 35)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .bottomLeading)

            // item3_item2_page_boutique : 81x10, round_black_70_25 (le CircleIndicator
            // est rogné par le padding 4/5 de ce conteneur de 10dp).
            RoundedRectangle(cornerRadius: 25)
                .fill(DS.blackA(70))
                .frame(width: 81, height: 10)
                .padding(.leading, 5)
                .padding(.bottom, 5)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .bottomLeading)
        }
    }

    // MARK: top_detail

    private var topDetail: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(info.categorie)
                .font(.system(size: 18, weight: .bold))
                .foregroundStyle(DS.white)
                .padding(.horizontal, 12)
                .padding(.top, 5)
                .padding(.bottom, 6)
                .frame(height: 35)
                .background(DS.black, in: RoundedRectangle(cornerRadius: 15))
                .padding(.leading, 5)
                .padding(.top, 4)
                .offset(y: catY)

            HStack(alignment: .top, spacing: 0) {
                Image("plce")
                    .resizable()
                    .scaledToFill()
                    .frame(width: 18, height: 18)
                    .clipped()
                Text(info.lieux)
                    .font(.system(size: 15, weight: .bold))
                    .foregroundStyle(DS.whiteA(50))
                    .padding(.top, 5)
                Spacer(minLength: 0)
            }
            .padding(.horizontal, 12)
            .padding(.vertical, 4)
            .frame(width: 175)
            .background(DS.black, in: RoundedRectangle(cornerRadius: 15))
            .padding(.leading, 5)
            .padding(.top, 5)
            .offset(x: emplX)
        }
        .padding(.trailing, 15)
    }

    // MARK: bottom_detail

    private var bottomDetail: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(info.itemCategorie)
                .font(.system(size: 18, weight: .bold))
                .foregroundStyle(DS.white)
                .lineLimit(1)
                .padding(.leading, 12)
                .padding(.trailing, 12)
                .padding(.top, 4)
                .padding(.bottom, 5)
                .frame(minWidth: 110, alignment: .leading)
                .background(DS.black, in: RoundedRectangle(cornerRadius: 15))
                .padding(.leading, 5)
                .padding(.bottom, 4)
                .offset(x: itemX)

            Text(info.prix)
                .font(.system(size: 15, weight: .bold))
                .foregroundStyle(DS.whiteA(50))
                .lineLimit(1)
                .padding(.leading, 12)
                .padding(.trailing, 16)
                .padding(.vertical, 3)
                .frame(minWidth: 120, alignment: .leading)
                .background(DS.black, in: RoundedRectangle(cornerRadius: 15))
                .padding(.leading, 5)
                .padding(.bottom, 4)
                .offset(x: prixX)

            Text(info.cite.freeRooms > 0 ? "Actuellement Disponible" : "Complet")
                .font(.system(size: 15, weight: .bold))
                .foregroundStyle(DS.whiteA(50))
                .padding(.horizontal, 12)
                .padding(.vertical, 3)
                .frame(height: 25)
                .background(DS.black, in: RoundedRectangle(cornerRadius: 15))
                .padding(.leading, 5)
                .offset(y: labelY)
        }
    }

    private func animateIn() {
        let anim = Animation.easeInOut(duration: 0.8)
        withAnimation(anim.delay(0.4)) { catY = 0; labelY = 0 }
        withAnimation(anim.delay(0.5)) { emplX = 0; itemX = 0 }
        withAnimation(anim.delay(0.6)) { prixX = 0 }
    }
}
