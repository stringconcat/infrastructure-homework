#!/bin/bash
set -e
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi
repositoryName=business_people
imageFullName=$repositoryName:$imageTag

echo [Business people STARTING] pushing image $imageFullName

echo [Business people ] pushing image...

echo [Business people ] creating docker image...
docker push $imageFullName

echo [Business people FINISHED] image $imageFullName pushed