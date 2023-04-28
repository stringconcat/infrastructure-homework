#!/bin/bash
set -e

imageTag=$1
if [ -z "$1" ]
  then
    echo No image tag provided. latest will be used
    imageTag=latest
fi

repositoryName=222513454914.dkr.ecr.us-east-2.amazonaws.com/mainapp
imageFullName=$repositoryName:$imageTag

echo [Main App STARTING] building $imageFullName...

echo [Main App] creating jar...
(exec ./gradlew bootJar --no-daemon)

echo [Main App] creating docker image...
docker build -t $imageFullName .

echo [Main App FINISHED] image has been built