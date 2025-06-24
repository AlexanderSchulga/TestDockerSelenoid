@echo off
cd /d "K:\TestDockerSelenoidJenkins\TestDockerSelenoid\src\test\resources"
start "Selenoid" /wait single64 selenoid start --vnc
timeout /t 5 /nobreak
start "Selenoid UI" single64 selenoid-ui start
