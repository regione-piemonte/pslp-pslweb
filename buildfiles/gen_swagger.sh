#!/bin/bash

# ******************************************************************************
# * Copyright Regione Piemonte - 2021
# * SPDX-License-Identifier: EUPL-1.2-or-later
# ******************************************************************************

# La directory dove sono presenti i jar del generatore csi-swagger
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
pushd $DIR > /dev/null

CSI_SWAGGER_GEN_HOME=/mnt/d/work/csi/pslp/devtools/csi-swagger-codegen
JAVA_HOME=/usr/bin
CLI_JAR=$CSI_SWAGGER_GEN_HOME/swagger-codegen-cli.jar
CUSTOM_GEN_JAR=$CSI_SWAGGER_GEN_HOME/csi-java-swagger-codegen-1.0.0.jar
SWAGGER_CP=$CLI_JAR:$CUSTOM_GEN_JAR

# Impostare solo per debug del generatore
DEBUG_OPTS=
# DEBUG_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=9797,server=y,suspend=y

function show_help() {
	cat << EOF
Usage: ${0##*/} [-d|--doc] [-j|--jaxrs] [-a|--angular] [-h|--help]
Generates code from thw Swagger definition. With no options asks for the type
of generation to execute, otherwise executes the chosen generations.

  -h            Display this help and exit
  -d, --doc     Generate the HTML documentation
  -j, --jaxrs   Generate the JAX-RS skeleton
  -a, --angular Generate the Angular stub
EOF
}
function invoke_jaxrs() {
  $JAVA_HOME/java -cp $SWAGGER_CP $DEBUG_OPTS io.swagger.codegen.SwaggerCodegen generate -i ../src/yaml/pslweb.yaml -l jaxrs-resteasy-eap-csi -o .. --config swagger_config_java.json
}
function invoke_angular() {
  $JAVA_HOME/java -cp $SWAGGER_CP $DEBUG_OPTS io.swagger.codegen.SwaggerCodegen generate -i ../src/yaml/pslweb.yaml -l typescript-angular -o ../../pslapi --config swagger_config_angular.json
}
function invoke_html() {
  $JAVA_HOME/java -cp $SWAGGER_CP $DEBUG_OPTS io.swagger.codegen.SwaggerCodegen generate -i ../src/yaml/pslweb.yaml -l html -o ../../pslapi/html_help
}
function ask() {
  # read -n1 -r -p "### $1 ###" key
  while true; do
    read -p "$1 [Y/N]? " yn
    case $yn in
      [Yy]* )
        $2
        break 2;;
      [Nn]* )
        break 2;;
      * ) ;;
    esac
  done
}

OPT_JAXRS=0
OPT_ANGULAR=0
OPT_HTML=0

while [[ $# -gt 0 ]]
do
  KEY="$1"

  case $KEY in
    -j|--jaxrs)
      OPT_JAXRS=1
      shift;;
    -a|--angular)
      OPT_ANGULAR=1
      shift;;
    -d|--doc)
      OPT_HTML=1
      shift;;
    -h|--help)
      show_help
      popd > /dev/null
      exit 0;;
    *)
      shift;;
  esac
done

if [[ $((OPT_JAXRS+OPT_ANGULAR+OPT_HTML)) -gt 0 ]]
then
  if [[ $OPT_JAXRS -eq 1 ]]; then invoke_jaxrs; fi
  if [[ $OPT_ANGULAR -eq 1 ]]; then invoke_angular; fi
  if [[ $OPT_HTML -eq 1 ]]; then invoke_html; fi

else
  ask "Generazione skeleton JAX-RS" invoke_jaxrs
  ask "Generazione stub Angular" invoke_angular
  ask "Generazione documentazione HTML" invoke_html
fi

popd > /dev/null
