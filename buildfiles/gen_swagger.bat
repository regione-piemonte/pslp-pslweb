REM ******************************************************************************
REM * Copyright Regione Piemonte - 2021
REM * SPDX-License-Identifier: EUPL-1.2-or-later
REM ******************************************************************************

@ECHO OFF
REM La directory dove sono presenti i jar del generatore csi-swagger
pushd "%~dp0"

set CSI_SWAGGER_GEN_HOME=C:\CSI\pslp\csi-swagger-codegen
set JAVA_HOME=C:\Jdk\jdk1.8.0_45\bin
set CLI_JAR=%CSI_SWAGGER_GEN_HOME%\swagger-codegen-cli.jar
set CUSTOM_GEN_JAR=%CSI_SWAGGER_GEN_HOME%\csi-java-swagger-codegen-1.0.0.jar
set SWAGGER_CP=%CLI_JAR%;%CUSTOM_GEN_JAR%
REM impostare solo per debug del generatore
set DEBUG_OPTS=
rem set DEBUG_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=9797,server=y,suspend=y

rem pause "#### generazione skeleton jaxrs ####"
:choice_skel
set /P c=Generazione skeleton JAXRS [Y/N]? 
if /I "%c%" EQU "Y" goto :skel
if /I "%c%" EQU "N" goto :choice_stub
goto :choice_skel
:skel
%JAVA_HOME%\java -cp %SWAGGER_CP% %DEBUG_OPTS% io.swagger.codegen.SwaggerCodegen generate -i ..\src\yaml\pslweb.yaml -l jaxrs-resteasy-eap-csi -o .. --config swagger_config_java.json

rem echo
rem pause "#### generazione stub angular2 ####"
:choice_stub
set /P c=Generazione stub Angular2 [Y/N]? 
if /I "%c%" EQU "Y" goto :stub
if /I "%c%" EQU "N" goto :choice_doc
goto :choice_stub
:stub
%JAVA_HOME%\java -cp %SWAGGER_CP% %DEBUG_OPTS% io.swagger.codegen.SwaggerCodegen generate -i ..\src\yaml\pslweb.yaml -l typescript-angular -o ..\..\pslapi\angular --config swagger_config_angular.json 

rem echo
rem pause "#### generazione documentazione html ####"
:choice_doc
set /P c=Generazione documentazione html [Y/N]? 
if /I "%c%" EQU "Y" goto :doc
if /I "%c%" EQU "N" goto :end
goto :choice_doc
:doc
%JAVA_HOME%\java -cp %SWAGGER_CP% io.swagger.codegen.SwaggerCodegen generate -i ..\src\yaml\pslweb.yaml -l html -o ..\..\pslapi\html_help

:end
popd