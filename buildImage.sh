#!/bin/bash
set -e
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi

(exec "${BASH_SOURCE%/*}/avatarsDicebear/buildImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/businessPeople/buildImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/persistence/buildImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/presentation/buildImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/quoteGarden/buildImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/useCasePeople/buildImage.sh" $imageTag)
