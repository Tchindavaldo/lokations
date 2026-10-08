#!/bin/sh
# Xcode Cloud : exécuté juste après le clone.
# 1. Autorise la résolution SPM (aucun Package.resolved n'est versionné au départ).
# 2. Injecte GoogleService-Info.plist depuis la variable secrète GOOGLE_SERVICE_INFO_PLIST_B64
#    (base64 du fichier). Sans elle, l'app démarre en mode démo.
set -e

defaults write com.apple.dt.Xcode IDEPackageOnlyUseVersionsFromResolvedFile -bool NO
defaults write com.apple.dt.Xcode IDEDisableAutomaticPackageResolution -bool NO

cd "$CI_PRIMARY_REPOSITORY_PATH/ios/Lokations"

if [ -n "$GOOGLE_SERVICE_INFO_PLIST_B64" ]; then
  echo "$GOOGLE_SERVICE_INFO_PLIST_B64" | base64 --decode > Lokations/GoogleService-Info.plist
  echo "GoogleService-Info.plist injecté."
else
  echo "GOOGLE_SERVICE_INFO_PLIST_B64 absent : build en mode démo."
fi

xcodebuild -resolvePackageDependencies -project Lokations.xcodeproj -scheme Lokations
