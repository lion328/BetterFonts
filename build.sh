#!/usr/bin/bash

set -e

./applyPatch.sh

echo "Building BetterFonts"
./gradlew build

