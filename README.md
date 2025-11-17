# 🏠 Lokations - Application Mobile de Gestion de Locations

## 📋 Vue d'ensemble

**Lokations** est une application Android native développée en **Kotlin** qui permet de gérer, consulter et administrer des annonces de locations immobilières (chambres, appartements, citées). L'application offre une expérience utilisateur riche avec navigation par fragments, gestion des utilisateurs via Firebase, et communication avec un backend via API REST.

**Version actuelle:** 1.0  
**Compilé pour:** Android 33 (API 33)  
**Minimum SDK:** 21  
**Target SDK:** 32

---

## 🎯 Fonctionnalités principales

### 1. **Authentification & Gestion des utilisateurs**
- Connexion/Inscription via Firebase Authentication
- Authentification par email et mot de passe
- Gestion des sessions utilisateur
- Écran de splash personnalisé

### 2. **Accueil & Navigation**
- Interface principale avec navigation par onglets (Bottom Navigation)
- 5 sections principales :
  - **Accueil (Home)** : Affichage des annonces principales
  - **Recherche** : Recherche et filtrage des propriétés
  - **Boutique** : Gestion des annonces et transactions
  - **Notifications** : Notifications utilisateur
  - **Paramètres** : Configuration du profil

### 3. **Affichage des Propriétés**
- Grille de propriétés avec images et informations
- Détails complets des propriétés (prix, localisation, chambres, etc.)
- Galerie d'images avec carrousel
- Commentaires et évaluations
- Statistiques par chambre (hebdomadaire, mensuel, annuel)

### 4. **Gestion des Annonces**
- Ajout de nouvelles annonces
- Modification des annonces existantes
- Suppression d'annonces
- Upload d'images, textes et fichiers audio
- Historique des transactions

### 5. **Fonctionnalités Avancées**
- Localisation géographique des propriétés
- Système de favoris
- Comptage des visites
- Notation et commentaires
- Statistiques de boutique
- Gestion des paiements

---

## 🏗️ Architecture & Structure du Projet

### Structure des répertoires

```
lokations/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/lokations/
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── network/          # Modèles réseau & API
│   │   │   │   │   │   │   ├── Post.kt
│   │   │   │   │   │   │   ├── Delete.kt
│   │   │   │   │   │   │   ├── Update.kt
│   │   │   │   │   │   │   ├── UploadImage.kt
│   │   │   │   │   │   │   ├── UploadZik.kt
│   │   │   │   │   │   │   ├── Uploadtexte.kt
│   │   │   │   │   │   │   ├── UserServicePost.kt
│   │   │   │   │   │   │   └── downloadFile.kt
│   │   │   │   │   │   └── LoggedInUser.kt
│   │   │   │   │   ├── LoginDataSource.kt
│   │   │   │   │   ├── LoginRepository.kt
│   │   │   │   │   └── Result.kt
│   │   │   │   ├── Activities/
│   │   │   │   │   ├── login.kt              # Écran de connexion
│   │   │   │   │   ├── activity_register.kt  # Inscription
│   │   │   │   │   ├── HomeActivity.kt       # Accueil principal
│   │   │   │   │   ├── MainActivity.kt       # Affichage des propriétés
│   │   │   │   │   ├── FrameLayoutActivity.kt # Détails des propriétés
│   │   │   │   │   ├── DetailCiteActivity.kt # Détails de la cité
│   │   │   │   │   ├── GetTest2Activity.kt
│   │   │   │   │   ├── MikelActivity.kt
│   │   │   │   │   └── activity_recycleViewFragment.kt
│   │   │   │   ├── Fragments/
│   │   │   │   │   ├── HomeFragment.kt       # Fragment accueil
│   │   │   │   │   ├── fragment_search.kt    # Recherche
│   │   │   │   │   ├── fragment_boutique.kt  # Boutique
│   │   │   │   │   ├── fragment_notif.kt     # Notifications
│   │   │   │   │   ├── fragment_param.kt     # Paramètres
│   │   │   │   │   ├── fragment_photo.kt     # Galerie photos
│   │   │   │   │   ├── fragment_detail.kt    # Détails
│   │   │   │   │   ├── fragment_comment.kt   # Commentaires
│   │   │   │   │   ├── fragment_locali.kt    # Localisation
│   │   │   │   │   ├── fragment_payement.kt  # Paiement
│   │   │   │   │   ├── fragment_chambre_info1-4.kt # Info chambres
│   │   │   │   │   ├── fragment_statistique_*.kt   # Statistiques
│   │   │   │   │   └── fragment_ligne*.kt    # Sections accueil
│   │   │   │   ├── Adapters/
│   │   │   │   │   ├── CustomAdapterHome.kt
│   │   │   │   │   ├── CustomAdapter.kt
│   │   │   │   │   ├── CustomAdapter2.kt
│   │   │   │   │   ├── CustomAdapter3.kt
│   │   │   │   │   ├── CustomAdapterCP.kt
│   │   │   │   │   ├── adapteur_recycleView_framelayout_home_data.kt
│   │   │   │   │   ├── adapteur_recycleView_chambre_info.kt
│   │   │   │   │   ├── adapteur_recycleView_statistique_chambre.kt
│   │   │   │   │   └── [autres adapters...]
│   │   │   │   ├── ViewModels/
│   │   │   │   │   ├── ItemsViewModel.kt
│   │   │   │   │   └── ItemsViewModelHome.kt
│   │   │   │   ├── Utils/
│   │   │   │   │   ├── RoundedImageView.kt
│   │   │   │   │   ├── MyPageTransformer.kt
│   │   │   │   │   ├── MyTabSelectedListner.kt
│   │   │   │   │   ├── TabsPagerAdapter.kt
│   │   │   │   │   ├── ViewPagerAdapter.kt
│   │   │   │   │   └── AdapteurPagerScroller.kt
│   │   │   │   └── [autres fichiers...]
│   │   │   ├── res/
│   │   │   │   ├── layout/                   # Fichiers XML de mise en page
│   │   │   │   ├── drawable/                # Images et ressources
│   │   │   │   ├── values/                  # Strings, colors, styles
│   │   │   │   └── anim/                    # Animations
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   ├── build.gradle                         # Configuration Gradle
│   └── google-services.json                 # Configuration Firebase
├── build.gradle
├── settings.gradle
├── gradle.properties
├── gradlew & gradlew.bat
└── README.md
```

---

## 🔌 Routes & Endpoints API

### Base URL
```
http://192.168.100.175:80/
```

### Endpoints disponibles

#### 1. **POST - Créer une annonce**
- **Endpoint:** `/lokation/post2.php`
- **Méthode:** POST
- **Type:** JSON
- **Classe:** `Post.kt`
- **Paramètres:**
  ```json
  {
    "name": "string",
    "age": "integer"
  }
  ```
- **Réponse:** Message de succès

#### 2. **PUT - Mettre à jour une annonce**
- **Endpoint:** `/lokation/update.php`
- **Méthode:** PUT
- **Type:** JSON
- **Classe:** `Update.kt`
- **Réponse:** Message de succès

#### 3. **DELETE - Supprimer une annonce**
- **Endpoint:** `/lokation/delete.php/{id}`
- **Méthode:** DELETE
- **Classe:** `Delete.kt`
- **Paramètres:**
  - `id` (Path Parameter): ID de l'annonce à supprimer
- **Réponse:** Confirmation de suppression

#### 4. **POST - Upload d'image**
- **Endpoint:** `/lokation/img.php`
- **Méthode:** POST (Multipart)
- **Classe:** `UploadImage.kt`
- **Paramètres:**
  - `image` (File): Fichier image
- **Réponse:** URL de l'image uploadée

#### 5. **POST - Upload de texte**
- **Endpoint:** `/lokation/txt.php`
- **Méthode:** POST (Multipart)
- **Classe:** `Uploadtexte.kt`
- **Paramètres:**
  - `text` (File): Fichier texte
- **Réponse:** Confirmation d'upload

#### 6. **POST - Upload de fichier audio**
- **Endpoint:** `/lokation/zik.php`
- **Méthode:** POST (Multipart)
- **Classe:** `UploadZik.kt`
- **Paramètres:**
  - `zik` (File): Fichier audio
- **Réponse:** Confirmation d'upload

---

## 🎬 Actions & Flux de Navigation

### Flux d'authentification
```
splash_activity
    ↓
login.kt (Connexion/Inscription)
    ├─→ activity_register.kt (Inscription)
    └─→ HomeActivity.kt (Accueil)
```

### Flux de navigation principale (HomeActivity)
```
HomeActivity (Bottom Navigation)
    ├─→ HomeFragment (Accueil)
    │   ├─→ fragment_ligne1-7.kt (Sections)
    │   ├─→ GetTest2Activity (Catégories)
    │   └─→ activity_recycleViewFragment (Chambres)
    │
    ├─→ fragment_search.kt (Recherche)
    │   └─→ FrameLayoutActivity (Détails)
    │
    ├─→ fragment_boutique.kt (Boutique)
    │   ├─→ fragment_boutique_historique_transcastion.kt
    │   ├─→ fragment_boutique_pub.kt
    │   └─→ fragment_boutique_statistique.kt
    │
    ├─→ fragment_notif.kt (Notifications)
    │
    └─→ fragment_param.kt (Paramètres)
```

### Flux de détails de propriété (FrameLayoutActivity)
```
FrameLayoutActivity
    ├─→ fragment_photo.kt (Galerie)
    ├─→ fragment_chambre_info1-4.kt (Infos)
    ├─→ fragment_payement.kt (Paiement)
    ├─→ fragment_comment.kt (Commentaires)
    └─→ fragment_locali.kt (Localisation)
```

---

## 🔧 Dépendances principales

### Kotlin & Android
- `org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10`
- `androidx.appcompat:appcompat:1.6.1`
- `androidx.core:core-ktx:1.10.1`
- `com.google.android.material:material:1.9.0`

### Networking & Serialization
- `com.squareup.retrofit2:retrofit:2.9.0`
- `com.squareup.retrofit2:converter-gson:2.9.0`
- `com.squareup.okhttp3:okhttp:4.10.0`
- `com.squareup.okhttp3:logging-interceptor:4.10.0`
- `com.google.code.gson:gson:2.10.1`

### Firebase
- `com.google.firebase:firebase-core:21.1.1`
- `com.google.firebase:firebase-auth-ktx:20.0.1`
- `com.google.firebase:firebase-firestore-ktx:24.7.1`
- `com.google.firebase:firebase-storage-ktx:20.2.1`

### UI & Animations
- `androidx.viewpager2:viewpager2:1.0.0`
- `com.airbnb.android:lottie:6.1.0`
- `me.relex:circleindicator:2.1.6`
- `com.squareup.picasso:picasso:2.8`
- `jp.wasabeef:picasso-transformations:2.4.0`
- `jp.wasabeef:blurry:4.0.0`

### Layout & RecyclerView
- `androidx.recyclerview:recyclerview:1.3.1`
- `androidx.cardview:cardview:1.0.0`
- `androidx.constraintlayout:constraintlayout:2.1.4`

### Lifecycle & ViewModel
- `androidx.lifecycle:lifecycle-livedata-ktx:2.6.1`
- `androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1`

### Coroutines
- `org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2`

---

## 🚀 Installation & Configuration

### Prérequis
- Android Studio (version récente)
- JDK 1.8+
- Gradle 7.3.1+
- Compte Firebase

### Étapes d'installation

1. **Cloner le projet**
   ```bash
   git clone <repository-url>
   cd lokations
   ```

2. **Configurer Firebase**
   - Placer le fichier `google-services.json` dans `app/`
   - Configurer les règles Firestore et Authentication dans Firebase Console

3. **Configurer l'API Backend**
   - Modifier l'URL de base dans les fichiers réseau si nécessaire
   - Vérifier que le serveur backend est accessible

4. **Compiler et exécuter**
   ```bash
   ./gradlew build
   ./gradlew installDebug
   ```

---

## 📱 Écrans principaux

### 1. **Écran de Connexion (login.kt)**
- Email et mot de passe
- Lien vers inscription
- Authentification Firebase
- Animations de transition

### 2. **Accueil (HomeFragment)**
- Carrousel d'images
- 7 sections de propriétés (ligne1-7)
- Catégories de recherche
- Accès aux chambres

### 3. **Recherche (fragment_search.kt)**
- Filtrage par critères
- Affichage des résultats
- Navigation vers détails

### 4. **Détails de Propriété (FrameLayoutActivity)**
- Galerie d'images
- Informations détaillées
- Commentaires
- Localisation
- Paiement

### 5. **Boutique (fragment_boutique.kt)**
- Ajout/Modification d'annonces
- Historique des transactions
- Statistiques
- Publicités

### 6. **Paramètres (fragment_param.kt)**
- Profil utilisateur
- Préférences
- Gestion du compte

---

## 🔐 Authentification & Sécurité

### Firebase Authentication
- Authentification par email/mot de passe
- Gestion des sessions
- Tokens de sécurité

### Permissions Android
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

### Configuration réseau
- Network Security Config: `@xml/network_config`
- Support du legacy external storage

---

## 📊 Modèles de données

### Modèles principaux

#### User (Utilisateur)
```kotlin
data class LoggedInUser(
    val userId: String,
    val displayName: String
)
```

#### Property (Propriété)
- Nom/Titre
- Prix
- Localisation
- Nombre de chambres
- Images
- Commentaires
- Évaluations

#### Transaction
- ID transaction
- Date
- Montant
- Statut
- Propriété associée

---

## 🎨 Thème & Styling

### Thème principal
- **Nom:** `Theme.Mmm`
- **Couleurs personnalisées:** Définies dans `values/colors.xml`
- **Animations:** Fade in/out transitions

### Animations
- `fade_in.xml` / `fade_out.xml` : Transitions standard
- `fade_in_bottom_nav.xml` / `fade_out_bottom_nav.xml` : Navigation inférieure

---

## 🧪 Tests

### Structure des tests
```
app/src/
├── test/          # Tests unitaires
└── androidTest/   # Tests instrumentalisés
```

### Frameworks de test
- JUnit 4.13.2
- Espresso 3.5.1
- AndroidJUnit Runner

---

## 📝 Fichiers de configuration

### build.gradle (Projet)
```gradle
plugins {
    id 'com.android.application' version '7.3.1'
    id 'org.jetbrains.kotlin.android' version '1.7.20'
    id 'com.google.gms.google-services' version '4.3.15'
}
```

### app/build.gradle
- Compilé pour SDK 33
- Min SDK 21, Target SDK 32
- View Binding activé
- RenderScript support activé

### AndroidManifest.xml
- 13 activités déclarées
- Permissions Internet et stockage
- Thème personnalisé
- Network Security Config

---

## 🐛 Dépannage courant

### Problème: Erreur de connexion API
**Solution:** Vérifier que l'adresse IP du serveur backend est correcte et accessible

### Problème: Firebase non initialisé
**Solution:** Vérifier que `google-services.json` est présent et correctement configuré

### Problème: Permissions manquantes
**Solution:** Demander les permissions à l'exécution pour Android 6.0+

---

## 📚 Documentation supplémentaire

### Fichiers clés à consulter
- `AndroidManifest.xml` : Déclaration des activités et permissions
- `build.gradle` : Dépendances et configuration
- `google-services.json` : Configuration Firebase

### Ressources externes
- [Documentation Android](https://developer.android.com/)
- [Firebase Documentation](https://firebase.google.com/docs)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Kotlin Documentation](https://kotlinlang.org/docs/)

---

## 👥 Contributeurs

Projet développé pour la gestion de locations immobilières.

---

## 📄 Licence

Ce projet est propriétaire et confidentiel.

---

## 📞 Support

Pour toute question ou problème, veuillez contacter l'équipe de développement.

---

**Dernière mise à jour:** Novembre 2025  
**Version:** 1.0  
**Statut:** En développement actif
