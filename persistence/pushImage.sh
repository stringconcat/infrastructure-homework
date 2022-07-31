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

echo [Persistence STARTING] pushing image $imageFullName

echo [Persistence ] pushing image...

echo [Persistence ] creating docker image...
docker push $imageFullName

echo [Persistence FINISHED] image $imageFullName pushed