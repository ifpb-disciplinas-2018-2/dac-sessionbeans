#!/usr/bin/env bash
mvn clean package
docker build -t ricardojob/app .
docker run -p 8082:8080 --name app ricardojob/app 

