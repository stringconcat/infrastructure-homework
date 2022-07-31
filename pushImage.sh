#!/bin/bash
set -e
imageTag=$1

if [ -z "$1" ];
  then
    echo No image tag provided. Latest will be used
    imageTag=latest
fi

(exec "${BASH_SOURCE%/*}/avatarsDicebear/pushImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/businessPeople/pushImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/persistence/pushImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/presentation/pushImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/quoteGarden/pushImage.sh" $imageTag)
(exec "${BASH_SOURCE%/*}/useCasePeople/pushImage.sh" $imageTag)