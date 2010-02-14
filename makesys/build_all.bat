@echo on

rem
rem setup the environment variables
rem
call setenv.bat


:BUILD
REM ****************** Building Code ******************
pushd ..\

rem
rem run maven
rem
rem testing the clean target
rem call mvn -e clean -Dmaven.repo.local=%MAVEN_REPO_LOCAL%
rem if %ERRORLEVEL% NEQ 0 goto FAILED
rem echo +++++++++++++
rem pause



call mvn %* -e clean install eclipse:clean eclipse:eclipse -Dmaven.repo.local=%MAVEN_REPO_LOCAL% -DdownloadSources=true -DdownloadJavadocs=true
if %ERRORLEVEL% NEQ 0 goto FAILED
echo +++++++++++++
pause



call mvn %* -e gwt:clean gwt:compile gwt:eclipse -Dmaven.repo.local=%MAVEN_REPO_LOCAL% -DdownloadSources=true -DdownloadJavadocs=true
if %ERRORLEVEL% NEQ 0 goto FAILED
echo +++++++++++++
pause


REM
REM call mvn -e gwt:test gwt:run -Dmaven.repo.local=%MAVEN_REPO_LOCAL%
if %ERRORLEVEL% NEQ 0 goto FAILED
echo +++++++++++++
pause

popd
goto END


:FAILED
echo ....ERROR Please check logs for further details, %ERRORLEVEL%  
popd
goto END



:END
rem
rem Restore the PATH if we backed it up above
rem
@if defined CMVN_OLD_PATH set PATH=%CMVN_OLD_PATH%
