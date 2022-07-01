#!/bin/bash
set -e
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi
repositoryName=avatars_decebear
imageFullName=$repositoryName:$imageTag

echo [Avatars decebear STARTING] pushing image $imageFullName

echo [Avatars decebear ] pushing image...

echo [Avatars decebear ] creating docker image...
docker push $imageFullName

echo [Avatars decebear FINISHED] image $imageFullName pushed