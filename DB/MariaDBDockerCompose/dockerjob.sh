#!/bin/bash

CREDENTIALS_FILE_NAME=MariaDBCredentials

DOCKER_COMPOSE_DIR="$(cd "$(dirname "$0")" && pwd)"

if [ -f "$DOCKER_COMPOSE_DIR/docker-compose.yml" ]; then

    if [ ! -f "$DOCKER_COMPOSE_DIR/$CREDENTIALS_FILE_NAME" ]; then
        echo -n $(echo geoshot | sha256sum -t | cut -d " " -f 1) > $DOCKER_COMPOSE_DIR/$CREDENTIALS_FILE_NAME
    fi

    CREDENTIALS=$(cat "$DOCKER_COMPOSE_DIR/$CREDENTIALS_FILE_NAME")

    sed -i "s/mmPASS_WORDmm/$CREDENTIALS/g" "$DOCKER_COMPOSE_DIR/docker-compose.yml"

    docker-compose -f "$DOCKER_COMPOSE_DIR/docker-compose.yml" up --detach

    until docker inspect -f '{{.State.Running}}' mariadb_geoshot &> /dev/null; do
        sleep 1
    done

    sed -i "s/$CREDENTIALS/mmPASS_WORDmm/g" "$DOCKER_COMPOSE_DIR/docker-compose.yml"

else
    echo "Please make sure that docker-compose.yml is in this directory."
    exit 1
fi

echo "mariadb_geoshot created and running!!"