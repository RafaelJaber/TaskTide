@echo off
setlocal enabledelayedexpansion

set applications=user task-scheduler notifications naming-server bff-scheduler
set docker_images=javanauta-user-microservice javanauta-task-scheduler-microservice javanauta-notifications-microservice javanauta-naming-server javanauta-bff-microservice

:: Função para verificar o status do Docker Compose
call :check_docker_compose_status

:: Função para imprimir uma mensagem de boas-vindas
call :print_welcome_message

:: Verifica o arquivo .env e as variáveis de ambiente
call :check_env_file_and_vars

:: Realiza o build com o Gradle das aplicações
call :gradle_build_all_apps

:: Remover as imagens Docker antigas
call :remove_old_docker_images

:: Construir novas imagens Docker para cada aplicação
call :build_docker_images

:: Executa o Docker Compose
call :execute_docker_compose

exit /b

:check_docker_compose_status
for /f "tokens=*" %%i in ('docker-compose ps --services --filter "status=running"') do (
    if not "%%i"=="" (
        echo Docker Compose is currently running. Stopping it now...
        docker-compose down
        echo Docker Compose has been stopped.
        exit /b 0
    )
)
echo Docker Compose is not running.
exit /b 0

:print_welcome_message
echo ==============================================================================================================
echo ==============================================================================================================
echo Hello, welcome to the automated script for deploying the application in an automated way, generating builds and Docker images.
echo ==============================================================================================================
echo ==============================================================================================================
timeout /t 3 >nul
exit /b 0

:check_env_file_and_vars
echo Checking .env file...
if not exist .env (
    echo Error: .env file not found.
    exit /b 1
)

call :check_env_var "SYSTEM_USER_EMAIL"
call :check_env_var "SYSTEM_USER_PASSWORD"
call :check_env_var "EMAIL_SENDER_FRIENDLY_NAME"
call :check_env_var "EMAIL_SENDER_FROM"
call :check_env_var "SEND_GRID_API_KEY"
call :check_env_var "SECRET_KEY"

echo .env file is properly configured.
exit /b 0

:check_env_var
for /f "tokens=*" %%i in ('findstr /r /c:"^%~1=" .env') do (
    if "%%i"=="" (
        echo Error: Environment variable %~1 is missing or empty in .env file.
        exit /b 1
    )
)
exit /b 0

:gradle_build_all_apps
echo Starting Gradle build for all applications...
for %%a in (%applications%) do (
    echo Building jar of %%a...
    cd .\%%a
    call gradlew clean build
    if errorlevel 1 (
        echo Gradle build failed. Exiting script.
        exit /b 1
    )
    cd ..
)
exit /b 0

:remove_old_docker_images
echo Removing old Docker images...
for %%i in (%docker_images%) do (
    docker images | findstr %%i >nul
    if not errorlevel 1 (
        echo Removing existing Docker image: %%i
        docker rmi -f %%i
    ) else (
        echo Docker image %%i not found, skipping removal.
    )
)
exit /b 0

:build_docker_images
echo Building Docker images for all applications...
for /l %%i in (1,1,5) do (
    for /f "tokens=1,* delims= " %%a in ("!applications!") do (
        set current_app=%%a
        set applications=%%b
    )
    for /f "tokens=1,* delims= " %%a in ("!docker_images!") do (
        set current_image=%%a
        set docker_images=%%b
    )
    echo Building Docker image for !current_app!...
    docker build -t !current_image! .\!current_app!
)
exit /b 0

:execute_docker_compose
echo Now, we will execute docker-compose to build and start the containers in detached mode using the .env file.
docker-compose --env-file .env up --build -d
timeout /t 5 >nul
echo Docker Compose has been successfully executed. You can now access the Swagger UI at http://localhost:8000/swagger-ui/index.html to explore and interact with the API.
exit /b 0
