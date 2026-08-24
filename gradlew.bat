@rem Set local directory to location of this script
@cd /d %~dp0

@rem Resolve the case where the batch file is executed with a relative path
@set "SCRIPT_PATH=%~f0"
@set "SCRIPT_DIR=%~dp0"

@rem Check if we are running from a different directory
if "%CD%" != "%SCRIPT_DIR%" (
    cd /d "%SCRIPT_DIR%"
)

@rem Set APP_HOME to the directory where this script is located
set APP_HOME=%~dp0

@rem Resolve symlinks in APP_HOME
:RESOLVE_SYMLINKS
set APP_HOME_DIR=%APP_HOME%
set APP_HOME=%~f1
if not "%APP_HOME%"=="%APP_HOME_DIR%" goto RESOLVE_SYMLINKS

@rem Set DEFAULT_JVM_OPTS
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

@rem Find java.exe
if defined JAVA_HOME goto FIND_JAVA_FROM_JAVA_HOME

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto EXECUTE

echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH. >&2
echo. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
goto EXIT

:FIND_JAVA_FROM_JAVA_HOME
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto EXECUTE

echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME% >&2
echo. >&2
echo Please set the JAVA_HOME variable in your environment to match the >&2
echo location of your Java installation. >&2
goto EXIT

:EXECUTE
@rem Setup the command line
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar

@rem Build the java command line
set JAVA_OPTS=%DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS%

@rem Execute the java command
"%JAVA_EXE%" %JAVA_OPTS% -Dorg.gradle.appname="%~nx0" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:EXIT
@exit /b %ERRORLEVEL%
