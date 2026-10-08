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
| CI | GitHub Actions -> TestFlight (`.github/workflows/ios-testflight.yml`) |

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
        Auth/                    SplashView, AuthFlowView, AuthComponents
        Home/                    HomeView, HomeCiteListView, HomeComponents (carrousel, sections, cartes)
        Search/                  SearchView, SearchCriterion, SearchComponents
        CiteDetail/              CiteDetailView, galerie, infos, chambres, avis, stats
        Payment/                 PaymentSheet
        Boutique/                BoutiqueView, BoutiqueProductsList, ProductEditorSheet, historique, stats
        Notifications/           NotificationsView
        Settings/                SettingsView
```

## Correspondance Android -> iOS

| Android | iOS |
|---|---|
| splash_activity | `SplashView` |
| login.kt / activity_register.kt | `AuthFlowView` |
| HomeActivity + BottomNavigationView | `MainTabView` |
| HomeFragment + fragment_ligne1..7 | `HomeView` |
| fragment_search | `SearchView` |
| FrameLayoutActivity + fragments photo/info/comment/stat | `CiteDetailView` |
| fragment_payement | `PaymentSheet` |
| fragment_boutique (+ historique / statistique) | `BoutiqueView` |
| activity_ajout_produit / activity_update_produit | `ProductEditorSheet` |
| fragment_notif | `NotificationsView` |
| fragment_param | `SettingsView` |

## Données

- Firestore : `users/user`, champ `listOfUsers = [{ item, prix }]` (même schéma qu'Android),
  lu / écrit par `FirestoreProductService`, exposé par `BoutiqueStore`.
- Cités, notifications, transactions, statistiques : `MockData` (en dur aussi côté Android).

## Mode démo

Sans `GoogleService-Info.plist` dans le bundle, `AppConfig.firebaseEnabled == false` :
connexion acceptée localement et produits gardés en mémoire.

## Build et TestFlight (GitHub Actions)

Workflow `.github/workflows/ios-testflight.yml` : runner macOS, signature cloud Apple via clé API,
envoi direct sur TestFlight. Se lance à chaque push sur `ios/**` (branches `feature/ios-native`, `main`)
ou à la main (onglet Actions > Run workflow). Numéro de build = numéro de run GitHub.

Secrets GitHub : `ASC_KEY_ID`, `ASC_ISSUER_ID`, `ASC_KEY_P8_B64` (clé rôle Admin, .p8 en base64),
`APPLE_TEAM_ID`, et optionnellement `GOOGLE_SERVICE_INFO_PLIST_B64`
(app iOS déclarée dans Firebase avec le bundle `com.rauval.lokation`).

`ci_scripts/ci_post_clone.sh` reste prêt si l'app est un jour branchée sur Xcode Cloud.
