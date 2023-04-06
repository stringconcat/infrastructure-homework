#!/bin/bash

echo Build app
./gradlew bootJar

echo Start docker containers
exec docker-compose up -d