#! /bin/bash
set -e

imageTag=$1
if [ -z "$1" ]
    then
    echo No image tag provided. latest will be used
    imageTag=latest
fi

repositoryName=222513454914.dkr.ecr.us-east-2.amazonaws.com/mainapp
imageFullName=$repositoryName:$imageTag

echo [Main app STARTING] pushing image $imageFullName...

echo [Main app] pushing image...
docker push $imageFullName

echo [Main app FINISHED] image $imageFullName pushed