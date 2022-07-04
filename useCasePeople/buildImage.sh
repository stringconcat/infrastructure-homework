#!/bin/bash
set -e 
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi
repositoryName=usecasepeople
imageFullName=$repositoryName:$imageTag

echo [Use case people STARTING] building $imageFullName

echo [Use case people] creating jar...
(exec "${BASH_SOURCE%/*}/../gradlew" bootJar --no-daemon)

echo [Use case people] creating docker image $imageFullName...
docker build -t $imageFullName "${BASH_SOURCE%/*}"

echo [Use case people FINISHED] image $imageFullName has been built

