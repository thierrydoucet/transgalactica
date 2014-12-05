#!/bin/bash

# Contrôle de la presence de JAVA
if [ -z "$JAVA_HOME" ]
then
    echo "[bash] No JAVA_HOME set"
    JAVA_CMD=`which java`
else 
    echo "[bash] Using JAVA_HOME : $JAVA_HOME"
    JAVA_CMD="$JAVA_HOME/bin/java"
fi

if [ ! -x $JAVA_CMD ]
then
    echo "[bash] Cannot find '$JAVA_CMD'!"
    exit 1
fi

# Determination du repertoire d'éxécution
CURRENT_DIR=`pwd`
RUN_DIR=`dirname $0`/../
cd $RUN_DIR

# Exécution du batch
echo "[bash] Using java command : $JAVA_CMD"
echo "[bash] Using work directory : `pwd`"
echo "[bash] Running webapp..."

$JAVA_CMD \
    -Dlogback.configurationFile="conf/logback.xml" \
    -DtransGalactica.configuration="file:conf/transGalactica.properties" \
    -Dmotd.url="file:conf/motd.xml" \
    -jar boot/jetty-runner-*.jar \
    --lib boot/extra \
    lib/*.war
	
echo "[bash] ... webapp ended with return code $?."
cd "$CURRENT_DIR"
