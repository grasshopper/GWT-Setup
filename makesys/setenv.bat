@echo off

set MAVEN_3P_REPO=%CD%\..\local_repo
set JAVA_HOME=C:\Devtools\Java\jdk1.6.0_16
set APP_ENGINE_VERSION=1.3.0
set APP_ENGINE_LIB=C:\Devtools\eclipse-64bit\plugins\com.google.appengine.eclipse.sdkbundle.1.3.0_1.3.0.v200912141120\appengine-java-sdk-%APP_ENGINE_VERSION%\lib


rem verify the directories exist
rem Create the directory if it does not exist
IF NOT EXIST %MAVEN_3P_REPO% mkdir %MAVEN_3P_REPO%

IF NOT EXIST %JAVA_HOME% GOTO JAVA_MISSING_ERROR

IF NOT EXIST %APP_ENGINE_LIB% GOTO APP_ENGINE_MISSING_ERROR



GOTO END





:JAVA_MISSING_ERROR
ECHO Missing JAVA 
ECHO %JAVA_HOME%
GOTO END

:APP_ENGINE_MISSING_ERROR
ECHO Missing Google App Engine 
ECHO %APP_ENGINE_LIB%
GOTO END


:END
