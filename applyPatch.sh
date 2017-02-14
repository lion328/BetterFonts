#!/usr/bin/bash

set -e

echo "Setting up workspace"
./gradlew setupDecompWorkspace

echo "Copying FontRenderer.java"
cp build/tmp/recompileMc/sources/net/minecraft/client/gui/FontRenderer.java src/main/java/net/minecraft/client/gui/FontRenderer.java

patch src/main/java/net/minecraft/client/gui/FontRenderer.java < src/main/java/net/minecraft/client/gui/FontRenderer.java.patch

