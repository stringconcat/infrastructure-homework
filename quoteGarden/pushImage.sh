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

echo [Quote garden STARTING] pushing image $imageFullName

echo [Quote garden] pushing image...

echo [Quote garden] creating docker image...
docker push $imageFullName

echo [Quote garden FINISHED] image $imageFullName pushed