@echo on


REM use 32 bit java
REM do not use quotes around the path as that causes maven issues
set JAVA_HOME=C:\Program Files (x86)\Java\jdk1.6.0_17
set APP_ENGINE_VERSION=1.3.0
set APP_ENGINE_LIB=C:\Devtools\eclipse-64bit\plugins\com.google.appengine.eclipse.sdkbundle.1.3.0_1.3.0.v200912141120\appengine-java-sdk-%APP_ENGINE_VERSION%\lib

REM ***** Set Maven Deploy-To Local Artifact Repository *****
set MAVEN_REPO_LOCAL=%CD%\..\local_repo
 


rem verify the directories exist
rem Create the directory if it does not exist
IF NOT EXIST %MAVEN_REPO_LOCAL% mkdir %MAVEN_REPO_LOCAL%

IF NOT EXIST %JAVA_HOME% GOTO JAVA_MISSING_ERROR

IF NOT EXIST %APP_ENGINE_LIB% GOTO APP_ENGINE_MISSING_ERROR


REM backup up our current PATH
set CMVN_OLD_PATH=%PATH%
set PATH=%JAVA_HOME%\bin;%PATH%
 

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
