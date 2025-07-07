@echo off
cd ".\src\test\resources"

:: Даем время на завершение всех операций
timeout /t 5 /nobreak

:: 1. Остановка Selenoid и удаление основного контейнера
start "Selenoid" /wait single64 selenoid stop --vnc
docker rm -f selenoid 2>nul

timeout /t 3 /nobreak

:: 2. Остановка Selenoid UI и удаление контейнера
start "Selenoid UI" /wait single64 selenoid-ui stop
docker rm -f selenoid-ui 2>nul

:: 3. Удаление ВСЕХ ОСТАВШИХСЯ контейнеров (включая временные)
for /f "tokens=1" %%i in ('docker ps -aq') do (
    echo Удаление контейнера: %%i
    docker rm -f %%i 2>nul
)

echo Все контейнеры (включая временные) остановлены и удалены.
