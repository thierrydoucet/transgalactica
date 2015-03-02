#!/bin/bash

# Contrôle des paramètres du script
argumentCount=$#
scriptName="`basename $0`"
if [ $argumentCount -ne  2 ];
then
  echo "Usage: $scriptName <Date de calcul au format AAAA-MM> <Repertoire de generation des fiches de salaire>"
  echo "Exemple : $scriptName 2012-11 /var/transGalactica/fichesSalaire"
  exit 1
fi

#récupération des arguments du script
DATE_CALCUL="$1"
GENERATION_DIR="$2"
TMP_COMPUTE_FILE=`mktemp /tmp/transGalactica.batchSalaire.computeStep.XXXXXXXXXX`

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
echo "[bash] Running job..."

$JAVA_CMD \
    -Dlogback.configurationFile="conf/transGalactica.logback.xml" \
    -Dspring.profiles.active="embedded" \
    -Duser.country=FR \
    -Duser.language=fr \
    -cp "boot/*:lib/*" \
    org.springframework.batch.core.launch.support.CommandLineJobRunner \
    "org.transgalactica.batch.salaire.context.AppConfig" \
    ficheSalaireJob \
    salaire.compute.date="$DATE_CALCUL-01" \
    salaire.compute.output.filename="file:$TMP_COMPUTE_FILE" \
    salaire.edit.output.directory="file:$GENERATION_DIR/"

echo "[bash] ... job ended with return code $?."
cd "$CURRENT_DIR"
