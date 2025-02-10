cd sonar-application\build\distributions
tar -xf sonar-application-25.2-SNAPSHOT.zip
cd sonarqube-25.2-SNAPSHOT\bin\windows-x86-64
Start-Process -NoNewWindow -FilePath .\StartSonar.bat