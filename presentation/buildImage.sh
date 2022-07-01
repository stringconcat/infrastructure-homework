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

echo [Presentation STARTING] building $imageFullName

echo [Presentation ] creating jar...
(exec "${BASH_SOURCE%/*}/../gradlew" bootJar -no-daemon)

echo [Presentation ] creating docker image...
docker build -t $imageFullName "${BASH_SOURCE%/*}"

echo [Presentation FINISHED] image has been built

