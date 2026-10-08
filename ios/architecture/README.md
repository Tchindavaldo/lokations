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
| CI | Xcode Cloud, `ci_scripts/ci_post_clone.sh` |

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

## Xcode Cloud

1. Créer le workflow sur le projet `ios/Lokations/Lokations.xcodeproj`, schéma `Lokations`.
2. Secret d'environnement `GOOGLE_SERVICE_INFO_PLIST_B64` = `base64 -i GoogleService-Info.plist`
   (app iOS déclarée dans la console Firebase avec le bundle `com.rauval.lokations`).
3. Pour l'archive / TestFlight : renseigner l'équipe de signature dans Xcode
   (icône 1024 déjà fournie dans `AppIcon`).
