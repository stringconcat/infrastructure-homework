#!/bin/bash
set -e 
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi
repositoryName=persistence
imageFullName=$repositoryName:$imageTag

echo [Persistence STARTING] building $imageFullName

echo [Persistence ] creating jar...
(exec "${BASH_SOURCE%/*}/../gradlew" bootJar -no-daemon)

echo [Persistence ] creating docker image...
docker build -t $imageFullName "${BASH_SOURCE%/*}"

echo [Persistence FINISHED] image has been built

