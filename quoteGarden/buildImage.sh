#!/bin/bash
set -e 
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi
repositoryName=quote_garden
imageFullName=$repositoryName:$imageTag

echo [Quote garden STARTING] building $imageFullName

echo [Quote garden ] creating jar...
(exec "${BASH_SOURCE%/*}/../gradlew" bootJar -no-daemon)

echo [Quote garden ] creating docker image...
docker build -t $imageFullName "${BASH_SOURCE%/*}"

echo [Quote garden FINISHED] image has been built

