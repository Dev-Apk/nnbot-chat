#!/bin/sh
APP_NAME="Gradle"
GRADLE_VERSION="8.10.2"
DIST_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
WRAPPER_JAR="$HOME/.gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$WRAPPER_JAR" ]; then
  mkdir -p "$HOME/.gradle/wrapper"
  curl -fsSL "$DIST_URL" -o /tmp/gradle-wrapper.zip || exit 1
  unzip -qo /tmp/gradle-wrapper.zip -d "$HOME/.gradle/wrapper" || exit 1
  rm -f /tmp/gradle-wrapper.zip
fi
exec java -jar "$WRAPPER_JAR" "$@"
