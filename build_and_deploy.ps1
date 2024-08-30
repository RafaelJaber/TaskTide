# Define arrays
$applications = @("user", "task-scheduler", "notifications", "naming-server", "bff-scheduler")
$docker_images = @("javanauta-user-microservice", "javanauta-task-scheduler-microservice", "javanauta-notifications-microservice", "javanauta-naming-server", "javanauta-bff-microservice")

# Função para verificar o status do Docker Compose
function Check-DockerComposeStatus {
    $runningContainers = docker-compose ps --services --filter "status=running"
    if ($runningContainers) {
        Write-Output "Docker Compose is currently running. Stopping it now..."
        docker-compose down
        Write-Output "Docker Compose has been stopped."
    } else {
        Write-Output "Docker Compose is not running."
    }
}

# Verifica o status do Docker Compose
Check-DockerComposeStatus

# Função para imprimir uma mensagem estilizada
function Print-WelcomeMessage {
    $message = "Hello, welcome to the automated script for deploying the application in an automated way, generating builds and Docker images."

    Write-Host "=============================================================================================================="
    Write-Host "=============================================================================================================="
    Write-Host "$message" -ForegroundColor Yellow
    Write-Host "=============================================================================================================="
    Write-Host "=============================================================================================================="
    Start-Sleep -Seconds 3
}

# Função para adicionar separadores visuais no console
function Print-Message {
    param (
        [string]$customMessage
    )

    Write-Host ""
    Write-Host "=============================================================================================================="
    Write-Host "=============================================================================================================="
    Write-Host "$customMessage" -ForegroundColor Yellow
    Write-Host "=============================================================================================================="
    Write-Host "=============================================================================================================="
    Write-Host ""
}

# Início do script
Print-WelcomeMessage

# Função para verificar a presença de uma variável no arquivo .env
function Check-EnvVar {
    param (
        [string]$varName
    )

    if (-not (Test-Path ".env")) {
        Write-Output "Error: .env file not found."
        exit 1
    }

    $envVar = Select-String -Path ".env" -Pattern "^$varName="
    if (-not $envVar) {
        Print-Message "Error: Environment variable $varName is missing or empty in .env file."
        exit 1
    }
}

# Verificar as variáveis de ambiente no .env
Print-Message "Checking .env file..."
Check-EnvVar "SYSTEM_USER_EMAIL"
Check-EnvVar "SYSTEM_USER_PASSWORD"
Check-EnvVar "EMAIL_SENDER_FRIENDLY_NAME"
Check-EnvVar "EMAIL_SENDER_FROM"
Check-EnvVar "SEND_GRID_API_KEY"
Check-EnvVar "SECRET_KEY"

Write-Output ".env file is properly configured."

# Realiza o build com o gradlew das aplicações
Print-Message "Starting Gradle build for all applications..."
foreach ($app in $applications) {
    Write-Output "Building jar of $app..."
    Set-Location -Path .\$app
    ./gradlew clean build

    if ($LASTEXITCODE -ne 0) {
        Write-Output "Gradle build failed. Exiting script."
        exit 1
    }
    Set-Location -Path ..
}

# Remover as imagens Docker antigas
Print-Message "Removing old Docker images..."
foreach ($image in $docker_images) {
    if (docker images | Select-String -Pattern $image) {
        Write-Output "Removing existing Docker image: $image"
        docker rmi -f $image
    } else {
        Write-Output "Docker image $image not found, skipping removal."
    }
}

# Construir novas imagens Docker para cada aplicação
Print-Message "Building Docker images for all applications..."
foreach ($app in $applications) {
    Write-Output "Building Docker image for $app..."
    docker build -t "${docker_images[$app]}" .\$app
}

Print-Message "Build and Docker image creation completed successfully!"

Print-Message "Now, we will execute docker-compose to build and start the containers in detached mode using the .env file."

docker-compose --env-file .env up --build -d
Start-Sleep -Seconds 5

Print-Message "Docker Compose has been successfully executed. You can now access the Swagger UI at http://localhost:8000/swagger-ui/index.html to explore and interact with the API."
