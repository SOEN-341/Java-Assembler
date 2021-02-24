@ECHO OFF
C:
CD "\_csse\soen341_2021_winter\_project\Sprint2"
rem Compile all test files
"C:\Program Files\Java\jdk1.8.0_121\bin\javac.exe" aunit.java
"C:\Program Files\Java\jdk1.8.0_121\bin\javac.exe" TestPosition.java
"C:\Program Files\Java\jdk1.8.0_121\bin\javac.exe" TestToken.java
rem Execute all tests
"C:\Program Files\Java\jdk1.8.0_121\bin\java.exe" TestPosition >  Tests.txt
"C:\Program Files\Java\jdk1.8.0_121\bin\java.exe" TestToken    >> Tests.txt
rem Run AUnit
"C:\Program Files\Java\jdk1.8.0_121\bin\java.exe" aunit Tests.txt
PAUSE
