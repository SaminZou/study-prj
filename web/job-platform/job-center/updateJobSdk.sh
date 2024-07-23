#!/bin/bash

# Rebuild SDK and update job worker reference

./gradlew :job-sdk:publishToMavenLocal

cd ../job-worker

./gradlew clean build