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

echo [Use case people STARTING] pushing image $imageFullName

echo [Use case people] pushing image...

echo [Use case people] creating docker image...
docker push $imageFullName

echo [Use case people FINISHED] image $imageFullName pushed