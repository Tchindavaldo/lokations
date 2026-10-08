# Architecture — Lokations iOS

Application SwiftUI native (iOS 17+, Xcode 16+), portage de l'app Android Kotlin.
Backend : Firebase Auth (email / mot de passe) + Firestore, via Swift Package Manager.

## Stack

| Élément | Choix |
|---|---|
| UI | SwiftUI, SF Symbols, Swift Charts |
| Navigation | `TabView` (5 onglets) + `NavigationStack` par onglet |
| État partagé | `ObservableObject` injectés par `.environmentObject` |
| Backend | FirebaseCore, FirebaseAuth, FirebaseFirestore (SPM, 12.x) |
| CI | Xcode Cloud (Start Build manuel) -> TestFlight |

## Arborescence

```
ios/
  CLAUDE.md                      règles du projet
  architecture/README.md         ce fichier
  Lokations/
    Lokations.xcodeproj          projet (dossier synchronisé, schéma partagé)
    ci_scripts/ci_post_clone.sh  résolution SPM + injection GoogleService-Info.plist
    Lokations/
      Assets.xcassets            images reprises de app/src/main/res/drawable
      App/
        LokationsApp.swift       @main, configure Firebase au démarrage
        RootView.swift           crée les stores, splash -> auth -> onglets
        MainTabView.swift        5 onglets
      Core/
        Theme/DS.swift           design system (couleurs, rayons, espacements)
        Config/AppConfig.swift   Firebase on/off, constantes Firestore
        Models/                  Cite, CiteSection, Product, AppNotification, Transaction, StatPeriod
        Services/                AuthService, ProductService (Firebase + démo), MockData
        Stores/                  SessionStore, BoutiqueStore, FavoritesStore
      Features/
        Auth/                    SplashView, AuthFlowView, AuthLoginView, AuthRegisterView, AuthInputField, AuthCommon, AuthWeightedColumn
        Home/                    HomeView, HomeCiteListView, HomeComponents (carrousel, sections, cartes)
        Search/                  SearchView, SearchToolbar, SearchTabStrip, SearchSectionBlock, SearchCiteCards
        CiteDetail/              CiteDetailView (maquette « Photo immersive »), CiteDetailGlass, CiteDetailInfo, CiteCommentView (feuille Avis), CiteChambreInfo*, CiteStatistique*
        Payment/                 PaymentView (+ PaymentComponents), présenté en .sheet
        Boutique/                BoutiqueView + pages Transaction, Statistique, Shop, Pub, BoutiqueProductFormView
        Notifications/           NotificationsView, NotifRow
        Settings/                SettingsView, SettingsRow, SettingsDetailSheet
```

## Correspondance Android -> iOS

| Android | iOS |
|---|---|
| splash_activity | `SplashView` |
| login.kt / activity_register.kt | `AuthFlowView` -> `AuthLoginView` / `AuthRegisterView` |
| HomeActivity + BottomNavigationView | `MainTabView` |
| HomeFragment + fragment_ligne1..7 | `HomeView` |
| fragment_search | `SearchView` |
| FrameLayoutActivity (remplacé par la maquette « B · Photo immersive ») + fragment_comment | `CiteDetailView`, `CiteCommentView` |
| fragment_chambre_info1..4 / inflate_chambre_infos | `CiteChambreInfoPager`, `CiteChambreInfoRow`, `CiteChambreInfo2View`, `CiteChambreInfo4View` |
| fragment_statistique_* / inflate_statistique_chambre | `CiteStatistiquePeriodeView`, `CiteStatistiqueChambreList`, `CiteStatistiqueChambreRow` |
| fragment_payement | `PaymentView` |
| fragment_boutique (+ historique / statistique / boutique / pub) | `BoutiqueView` |
| activity_ajout_produit / activity_update_produit | `BoutiqueProductFormView` (.sheet) |
| fragment_notif | `NotificationsView` |
| fragment_param | `SettingsView` |

## Données

- Firestore : `users/user`, champ `listOfUsers = [{ item, prix }]` (même schéma qu'Android),
  lu / écrit par `FirestoreProductService`, exposé par `BoutiqueStore`.
- Cités, notifications, transactions, statistiques : `MockData` (en dur aussi côté Android).

## Mode démo

Sans `GoogleService-Info.plist` dans le bundle, `AppConfig.firebaseEnabled == false` :
connexion acceptée localement et produits gardés en mémoire.

## Build et TestFlight (Xcode Cloud)

Workflow Xcode Cloud « Default » : condition de départ manuelle (Start Build), action Archive iOS,
post-action TestFlight Internal Testing. `ci_scripts/ci_post_clone.sh` résout les packages SPM et
injecte `GoogleService-Info.plist` depuis la variable secrète `GOOGLE_SERVICE_INFO_PLIST_B64`
(sinon mode démo). Choisir Xcode 26+ dans Environment pour Liquid Glass.
