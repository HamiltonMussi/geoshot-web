#!/bin/bash

DOCKER_COMPOSE_DIR="$(cd "$(dirname "$0")" && pwd)"

DDLS_DIR=DDLs

CREDENTIALS_FILE_NAME=MariaDBCredentials

for SQL_SCRIPTS in $(ls $DOCKER_COMPOSE_DIR/$DDLS_DIR); do
    
    mariadb -u root --port=3306 \
    --password=$(cat $DOCKER_COMPOSE_DIR/$CREDENTIALS_FILE_NAME) \
    -e "$(cat $DOCKER_COMPOSE_DIR/$DDLS_DIR/$SQL_SCRIPTS)"

done