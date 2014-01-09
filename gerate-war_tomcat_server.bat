@REM from http://maven.apache.org/download.cgi
@REM 2.Add the M2_HOME environment variable by opening up the system properties (WinKey + Pause), selecting the "Advanced" tab, and the "Environment Variables" button, then adding the M2_HOME variable in the user variables with the value C:\Program Files\Apache Software Foundation\apache-maven-3.1.1. Be sure to omit any quotation marks around the path even if it contains spaces. Note: For Maven   2.0.9, also be sure that the M2_HOME doesn't have a '\' as last character. 
@REM 3.In the same dialog, add the M2 environment variable in the user variables with the value %M2_HOME%\bin.
@REM 4.Optional: In the same dialog, add the MAVEN_OPTS environment variable in the user variables to specify JVM properties, e.g. the value -Xms256m -Xmx512m. This environment variable can be used to supply extra options to Maven.
@REM 5.In the same dialog, update/create the Path environment variable in the user variables and prepend the value %M2% to add Maven available in the command line.
@REM 6.In the same dialog, make sure that JAVA_HOME exists in your user variables or in the system variables and it is set to the location of your JDK, e.g. C:\Program Files\Java\jdk1.5.0_02 and that %JAVA_HOME%\bin is in your Path environment variable.
@REM 7.Open a new command prompt (Winkey + R then type cmd) and run mvn --version to verify that it is correctly installed.



@SET CURR_CD=%CD%



@IF NOT "%M2_HOME%"=="" GOTO MAVEN_OK
@ECHO Maven home path not defined, using portable version

@IF EXIST apache-maven-3.1.1 GOTO MAVEN_PATH_OK
@ECHO .
@ECHO .
@ECHO .
@ECHO Ready to unzip portable version...
@PAUSE
.\tools\7za.exe x -o".\" .\tools\apache-maven-3.1.1.zip
@IF ERRORLEVEL 1 GOTO ERROR

:MAVEN_PATH_OK
@SET M2_HOME=%CD%\apache-maven-3.1.1
@SET M2=%M2_HOME%\bin
@SET MAVEN_OPTS=-Xms256m -Xmx512m
@SET PATH=%PATH%;%M2%

:MAVEN_OK

@ECHO Maven home path defined: "%M2_HOME%"



@IF NOT "%JAVA_HOME%"=="" GOTO JAVA_OK
@ECHO Java home path not defined, using portable version

@IF EXIST jdk1.6.0_20 GOTO JAVA_PATH_OK
@ECHO .
@ECHO .
@ECHO .
@ECHO Ready to unzip portable version...
@PAUSE
.\tools\7za.exe x -o".\" .\tools\jdk1.6.0_20.zip
@IF ERRORLEVEL 1 GOTO ERROR

:JAVA_PATH_OK
@SET JAVA_HOME=%CD%\jdk1.6.0_20
@SET PATH=%PATH%;%JAVA_HOME%\bin

:JAVA_OK

@ECHO Java home path defined: "%JAVA_HOME%"



@ECHO .
@ECHO .
@ECHO .
@ECHO Ready to gerate war archive ...
@PAUSE
@CD smal
mvn.bat clean compile war:war
@IF ERRORLEVEL 1 GOTO ERROR



:ERROR
@ECHO Error!
@GOTO END

:OK
@ECHO Ok!

:END
@CD %CURR_CD%
@PAUSE
