#!/bin/bash
applications=("user" "task-scheduler" "notifications" "naming-server" "bff-scheduler")
docker_images=("javanauta-user-microservice" "javanauta-task-scheduler-microservice" "javanauta-notifications-microservice" "javanauta-naming-server" "javanauta-bff-microservice")

check_docker_compose_status() {
    # Verifica se há containers do Docker Compose em execução
    if docker-compose ps --services --filter "status=running" | grep -q .; then
        echo "Docker Compose is currently running. Stopping it now..."
        docker-compose down
        echo "Docker Compose has been stopped."
    else
        echo "Docker Compose is not running."
    fi
}
check_docker_compose_status

# Função para imprimir uma mensagem estilizada
print_welcome_message() {
    local message="Hello, welcome to the automated script for deploying the application in an automated way, generating builds and Docker images."

    local green='\033[0;32m'
    local yellow='\033[1;33m'
    local reset='\033[0m'

    echo -e "${green}"
    echo "=============================================================================================================="
    echo "=============================================================================================================="
    echo -e "${yellow}${message}${green}"
    echo "=============================================================================================================="
    echo "=============================================================================================================="
    echo -e "${reset}"
    sleep 3
}


print_separator() {
  echo ""
  echo "=============================================================================================================="
  echo ""
}

# Função para adicionar separadores visuais no console
print_message() {
    local custom_message="$1"

    # Cores
    local green='\033[0;32m'
    local yellow='\033[1;33m'
    local reset='\033[0m'  # Sem cor

    # Mensagem estilizada com a mensagem personalizada
    echo -e "${green}"
    echo " "
    echo "=============================================================================================================="
    echo "=============================================================================================================="
    echo -e "${yellow}${custom_message}${green}"
    echo "=============================================================================================================="
    echo "=============================================================================================================="
    echo " "
    echo -e "${reset}"
}

# Ínicio do script
print_welcome_message

# Função para verificar a presença de uma variável no arquivo .env
check_env_var() {
    local var_name="$1"
    local var_value

    # Verifica se a variável está presente e não está vazia
    var_value=$(grep -E "^${var_name}=" .env | cut -d '=' -f2-)

    if [ -z "$var_value" ]; then
        print_separator
        echo "Error: Environment variable ${var_name} is missing or empty in .env file."
        print_separator
        exit 1
    fi
}

# Verifica se o arquivo .env existe
print_message "Checking .env file..."
if [ ! -f .env ]; then
    echo "Error: .env file not found."
    print_separator
    exit 1
fi

# Verificar as variáveis de ambiente no .env
check_env_var "SYSTEM_USER_EMAIL"
check_env_var "SYSTEM_USER_PASSWORD"
check_env_var "EMAIL_SENDER_FRIENDLY_NAME"
check_env_var "EMAIL_SENDER_FROM"
check_env_var "SEND_GRID_API_KEY"
check_env_var "SECRET_KEY"

echo ".env file is properly configured."


# Realiza o build com o gradlew das aplicações
print_message "Starting Gradle build for all applications..."
for i in "${!applications[@]}"; do
  echo "Building jar of ${applications[$i]}..."
  cd ./"${applications[$i]}" || exit
  ./gradlew clean build

  # shellcheck disable=SC2181
  if [ $? -ne 0 ]; then
      echo "Gradle build failed. Exiting script."
      exit 1
  fi
  cd - || exit
done

# Remover as imagens Docker antigas
print_message "Removing old Docker images..."
for image in "${docker_images[@]}"; do
    if docker images | grep -q "$image"; then
        echo "Removing existing Docker image: $image"
        docker rmi -f "$image"
    else
        echo "Docker image $image not found, skipping removal."
    fi
done

# Construir novas imagens Docker para cada aplicação
print_message "Building Docker images for all applications..."
for i in "${!applications[@]}"; do
    echo "Building Docker image for ${applications[$i]}..."
    docker build -t "${docker_images[$i]}" ./"${applications[$i]}"
done

print_message "Build and Docker image creation completed successfully!"

print_message "Now, we will execute docker-compose to build and start the containers in detached mode using the .env file."

docker-compose --env-file .env up --build -d
sleep 5

print_message "Docker Compose has been successfully executed. You can now access the Swagger UI at http://localhost:8000/swagger-ui/index.html to explore and interact with the API."
