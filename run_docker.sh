#!/bin/sh

docker run -p 9000:9000 --network="host" --env-file ./env.list biletado-personal:latest