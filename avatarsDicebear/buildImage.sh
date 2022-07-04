#!/bin/bash
set -e 
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi
repositoryName=avatars_dicebear
imageFullName=$repositoryName:$imageTag

echo [Avatars DiceBear STARTING] building $imageFullName
echo BASH_SOURCE: "${BASH_SOURCE%/*}"
echo [Avatars DiceBear] creating jar...
(exec "${BASH_SOURCE%/*}/../gradlew" bootJar --no-daemon)

echo [Avatars DiceBear] creating docker image...
docker build -t $imageFullName "${BASH_SOURCE%/*}"

echo [Avatars DiceBear FINISHED] image has been built

