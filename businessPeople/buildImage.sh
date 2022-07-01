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

echo [Business people STARTING] building $imageFullName

echo [Business people] creating jar...
(exec "${BASH_SOURCE%/*}/../gradlew" bootJar -no-daemon)

echo [Business people] creating docker image...
docker build -t $imageFullName "${BASH_SOURCE%/*}"

echo [Business people FINISHED] image has been built

