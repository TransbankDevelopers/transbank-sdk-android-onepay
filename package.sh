#!/bin/sh

set -e

./gradlew clean assemble
cp onepay/build/outputs/aar/onepay-release.aar .
zip -r9 "sdk-android-onepay-$TRAVIS_TAG.zip" onepay-release.aar
