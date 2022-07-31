#!/bin/bash
set -e
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi
repositoryName=presentation
imageFullName=$repositoryName:$imageTag

echo [Presentation STARTING] pushing image $imageFullName

echo [Presentation ] pushing image...

echo [Presentation ] creating docker image...
docker push $imageFullName

echo [Presentation FINISHED] image $imageFullName pushed