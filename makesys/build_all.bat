@echo on

rem
rem setup the environment variables
rem
call setenv.bat

rem
rem add the JAVA_HOME to the path
rem
set OLD_PATH=%PATH%
set PATH=%JAVA_HOME%\bin;%PATH%


:BUILD
pushd ..
rem
rem run maven
rem
rem call mvn -e clean gwt:clean gwt:compile gwt:eclipse
rem echo +++++++++++++
rem pause

call mvn -e clean gwt:clean gwt:compile gwt:eclipse gwt:run
echo +++++++++++++
pause

call mvn -e clean gwt:clean gwt:compile gwt:eclipse gwt:run gwt:test
echo +++++++++++++
pause

call mvn -e clean gwt:clean gwt:compile gwt:eclipse gwt:run gwt:test -DdownloadSources=true -DdownloadJavadocs=true
echo +++++++++++++
pause


rem call mvn -e clean install eclipse:clean eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true
if %ERRORLEVEL% NEQ 0 goto FAILED

popd
goto END


:FAILED
echo ....ERROR Please check logs for further details, %ERRORLEVEL%  
popd
goto END



:END
rem
rem return the path to its old value
rem
set PATH=%OLD_PATH%

