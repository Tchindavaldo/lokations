# Consignes projet — Lokations iOS (SwiftUI natif)

Ce fichier est **versionné** : ses règles s'appliquent à toute session Claude Code
qui travaille dans `ios/`. Adapté des consignes du projet yaammoo.

> **13 règles numérotées R1 → R13.** Toute nouvelle règle reçoit le numéro suivant
> et le total ci-dessus est mis à jour.

## R1 — Style de réponse (OBLIGATOIRE)

Réponses COURTES, droit au but. Compte rendu après modif : **3 lignes MAX**.
Question fermée : 1 à 2 phrases. Pas de liste des fichiers modifiés, pas d'extraits
de code déjà écrits, pas de « Tu veux que je… ? » sauf demande explicite.

## R2 — Périmètre (OBLIGATOIRE)

Le travail iOS se fait UNIQUEMENT dans `ios/`. Ne jamais modifier l'app Android
(`app/`, Gradle) sans demande explicite.

## R3 — À lire en début de session (OBLIGATOIRE)

Lire `ios/architecture/README.md` (1 seul `Read`) avant de travailler. Pas d'agent
Explore pour « découvrir » le projet. **Tenir à jour** ce README dès qu'un fichier,
une feature ou un store est ajouté, renommé ou supprimé.

## R4 — Architecture & modularité (OBLIGATOIRE)

- Fichier : viser ~400 lignes, **500 = plafond DUR** ; au-delà, découper.
- Un fichier = une responsabilité.
- `App/` : point d'entrée, racine, onglets.
- `Core/` : `Theme/` (DS), `Config/`, `Models/`, `Services/` (accès Firebase purs,
  sans état, derrière un protocole), `Stores/` (état partagé).
- `Features/<Feature>/Views` + `Features/<Feature>/Components` : chaque feature isolée.
- Le projet utilise un dossier synchronisé Xcode 16 : tout fichier ajouté sous
  `Lokations/` est compilé automatiquement, ne pas éditer le `.pbxproj` pour ça.

## R5 — Branches Git (OBLIGATOIRE)

Jamais de code directement sur `main`. Préfixes : `feature/<sujet>`,
`debug/<sujet>`, `backup/<sujet>`. Déjà sur une branche de travail : on continue
dessus. Branche iOS actuelle : `feature/ios-native`.

## R6 — État partagé (OBLIGATOIRE)

Les `ObservableObject` de `Core/Stores/` injectés via `.environmentObject` sont la
source de vérité : `SessionStore` (auth), `BoutiqueStore` (produits Firestore),
`FavoritesStore`. Pas de singleton global, pas de passage de binding sur plus de
2 niveaux. L'état local d'écran reste en `@State`.

## R7 — Services & erreurs

Chaque accès réseau / Firebase passe par un service (`Core/Services/`) derrière un
protocole, avec une implémentation démo. Tout `catch` affiche un feedback
utilisateur (alerte ou message) ; jamais de `try?` silencieux sur une écriture.

## R8 — Secrets & configuration

Rien en dur. `GoogleService-Info.plist` n'est pas versionné : en local on le dépose
dans `ios/Lokations/Lokations/`, en CI il est injecté depuis le secret `GOOGLE_SERVICE_INFO_PLIST_B64`.
Sans lui, l'app tourne en mode démo. Constantes Firestore : `AppConfig`.

## R9 — Emojis : statut seulement (OBLIGATOIRE)

Aucun emoji décoratif (code, commentaires, doc, logs, commits). Icônes UI : celles
d'Android importées dans `Assets.xcassets` (même nom que le drawable), SF Symbols
seulement si Android n'en a pas. Autorisés : `⚠️` `✅` `❌` `✕`.

## R10 — Jamais de composant partagé entre écrans (OBLIGATOIRE)

Un composant d'UI utilisé par un autre écran n'est jamais modifié pour l'écran
courant : on le **duplique** dans la feature courante, préfixé par le domaine
(ex. `SearchResultRow` vs `HomeCiteCard`), avec un commentaire d'en-tête.
Les modèles, services et utilitaires sans rendu restent partagés.

## R11 — Builds : TOUJOURS demander avant (OBLIGATOIRE)

Aucun build ni test sans accord explicite. Les builds iOS passent par
**Xcode Cloud** (lancement manuel, schéma partagé `Lokations`, post-action TestFlight). Sans build, vérifier par relecture
et le dire en 1 phrase.

## R12 — Couleurs & dimensions : design system `DS` uniquement (OBLIGATOIRE)

Toute couleur passe par `DS` (`Core/Theme/DS.swift`) : jamais de `Color(red:…)` ni
hex dans une vue (`DS.android(0x…)` reprend une couleur littérale d'un layout XML).
Les dimensions recopiées d'un layout Android (dp -> pt, sp -> pt) sont permises
dans la vue qui reproduit ce layout.
Composants système (`NavigationStack`, `.sheet`, `ScrollView`, `TabView(.page)`,
`Swift Charts`…) seulement s'ils rendent exactement le layout Android (R13).

## R13 — Fidélité au design Android (OBLIGATOIRE)

L'app iOS est une **copie exacte** du design Android (`app/src/main/res/layout`,
`drawable`, `values`). Chaque écran reproduit son layout XML : mêmes images, icônes,
couleurs, textes, tailles (1 dp = 1 pt, 1 sp = 1 pt), marges, rayons, ordre des
éléments. Rien d'inventé. On garde quand même les composants iOS natifs récents tant que le
rendu reste celui d'Android : `TabView` natif (Liquid Glass sur iOS 26) avec les
icônes et l'ordre Android, `.glassEffect()` sous `if #available(iOS 26, *)` (repli
iOS 17 sur le style Android), `NavigationStack`, `.sheet`, gestes, haptique.
Chaque vue indique en en-tête le layout XML qu'elle reproduit.
