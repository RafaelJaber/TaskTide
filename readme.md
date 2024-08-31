[JAVA_BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[SQLSERVER_BADGE]: https://img.shields.io/badge/Microsoft%20SQL%20Server-CC2927?style=for-the-badge&logo=microsoft%20sql%20server&logoColor=white
[SWAGGER_BADGE]: https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white
[DOCKER_BADGE]: https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[MONGO_BADGE]: https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white
[POSTGRES_BADGE]: https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white
[THYMELEAF_BADGE]: https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white
[INSOMNIA_BADGE]: https://img.shields.io/badge/Insomnia-black?style=for-the-badge&logo=insomnia&logoColor=5849BE

<h1 align="center" style="font-weight: bold;">Task Tide 📋</h1>


<div style="text-align: center;">

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![Docker][DOCKER_BADGE]
![Swagger][SWAGGER_BADGE]
![MongoDB][MONGO_BADGE]
![Postgres][POSTGRES_BADGE]
![Thymeleaf][THYMELEAF_BADGE]
![Insomnia][INSOMNIA_BADGE]

[//]: # (![MicrosoftSQL Server][SQLSERVER_BADGE])

</div>


<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#description">Descrição do Projeto</a> • 
 <a href="#motivation">Motivação do Projeto</a> • 
 <a href="#started">Getting Started</a> • 
 <a href="#routes">Endpoints da API</a> •
 <a href="#colab">Colaboradores</a> •
</p>

O **Projeto TaskTide** foi desenvolvido durante o curso [Javanautas](https://javanauta.com.br/) com o propósito de aprimorar minhas habilidades em **Java Spring Boot** e explorar novas tecnologias no ecossistema Java. Ao longo do curso, foram abordados diversos tópicos fundamentais para o desenvolvimento de aplicações modernas e escaláveis, incluindo:

- **Uso de Bancos de Dados:** Implementação de persistência utilizando **MongoDB** e **PostgreSQL**, aproveitando o melhor de ambos os bancos para diferentes cenários de armazenamento de dados.

- **Comunicação entre Microserviços:** Utilização do **OpenFeign** para facilitar a comunicação entre microserviços, promovendo uma arquitetura mais flexível e modular.

Além dos conceitos e práticas apresentados durante o curso, também adicionei aprimoramentos ao projeto, aplicando meus conhecimentos em desenvolvimento com Spring Boot, tais como:

- **Naming Server:** Integração de um servidor de nomes para gerenciar e resolver dinamicamente a localização dos serviços, melhorando a escalabilidade e a resiliência do sistema.

- **Automatizações com Scripts Bash:** Criação de scripts Bash para automatizar tarefas de construção e implantação, otimizando o fluxo de trabalho e reduzindo a possibilidade de erros manuais.

- **Outras Tecnologias:** Implementação de diversas tecnologias avançadas que agregam valor ao projeto, refletindo as melhores práticas em desenvolvimento de software.

Este projeto representa uma síntese do conhecimento adquirido durante o curso e minha experiência prévia em desenvolvimento Java, demonstrando a capacidade de aplicar conceitos teóricos em soluções práticas.


## 💻 Tecnologias

Este projeto utiliza as seguintes tecnologias e frameworks:

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento backend.
- **Spring Boot**: Framework que facilita a criação de aplicações Spring autônomas e de produção.
- **Spring Security**: Módulo do Spring utilizado para implementar segurança e controle de acesso na aplicação.
- **Spring Data JPA**: Abstração de persistência de dados baseada no JPA.
- **Spring Data MongoDB**: Integração com o banco de dados NoSQL MongoDB.
- **Spring Cloud**: Conjunto de ferramentas para construção de sistemas distribuídos e microserviços.
  - **Eureka Server**: Serviço de descoberta de microserviços.
  - **Eureka Client**: Cliente para registro e descoberta de serviços no Eureka.
  - **OpenFeign**: Cliente HTTP que facilita a comunicação entre microserviços.
- **JWT (JSON Web Token)**: Implementação de autenticação baseada em tokens.
- **MapStruct**: Gerador de mapeamentos de objetos Java.
- **SendGrid**: Serviço de envio de emails.
- **Thymeleaf**: Motor de templates para construção de páginas web dinâmicas.
- **PostgreSQL**: Banco de dados relacional utilizado para persistência dos dados.
- **MongoDB**: Banco de dados NoSQL utilizado para armazenamento de dados.
- **Swagger (SpringDoc OpenAPI)**: Ferramenta para documentação e teste de APIs.
- **Docker**: Ferramenta para criação e gerenciamento de containers, utilizada para isolar e executar as aplicações.


<h2 id="description">📝 Descrição do Projeto</h2>

O **TaskTide** é um projeto desenvolvido para gerenciar o cadastro de usuários, incluindo seus endereços e telefones, e possibilitar a criação e gestão de tarefas. O sistema é projetado para enviar um e-mail de lembrete ao usuário com uma antecedência de 1 hora para a execução de cada tarefa.

<h2 id="motivation">🌟 Motivação</h2>

A motivação para a criação deste projeto surgiu da necessidade de consolidar e aprimorar habilidades em desenvolvimento com Java e Spring Boot, além de explorar conceitos avançados de arquitetura de microsserviços. O projeto foi estruturado para incorporar boas práticas, como o uso de um NamingService para gerenciamento de serviços e um BFF (Backend For Frontend) para centralização de requisições. O BFF é o único ponto de entrada para os microsserviços internos: UserMicroservices, TaskSchedulerMicroservice e NotificationMicroservice, que operam em uma rede interna protegida dentro de um ambiente Docker.

## Arquitetura do Projeto

### Arquitetura do Projeto

Abaixo está a representação da arquitetura do **TaskTide** e a interação entre os serviços:

### Arquitetura do Projeto

Abaixo está a representação da arquitetura do **TaskTide** e a interação entre os serviços:

```plaintext
                              +-----------------------+
                              |    Naming Service     |
                              |    (Porta 8761)       |
                              +-----------+-----------+
                                          |
                                          |
                              +-----------v-----------+
                              |          BFF          |
                              | (Backend For Frontend)|
                              |    (Porta 8000)       |
                              +-----------+-----------+
                                          |
           +------------------------------+-------------------------------+
           |                              |                               |
+----------v-----------+    +-------------v-------------+      +----------v-------------+
|  UserMicroservices   |    | TaskSchedulerMicroservice |      |NotificationMicroservice|
|(Cadastro de Usuários)|    |(Gerenciamento de Tarefas) |      |   (Envio de E-mails)   |
+----------+-----------+    +--------------+------------+      +------------------------+
           |                               |                            
+----------v----------+           +--------v----------+             
| PostgreSQL DB       |           |      MongoDB      |             
|(Dados dos Usuários) |           |(Dados das Tarefas)|             
+---------------------+           +-------------------+            
                       
```


<h2 id="started">🚀 Getting started</h2>

### Pré-requisitos

Antes de começar, certifique-se de ter os seguintes softwares instalados em sua máquina:

- **[Docker](https://www.docker.com/)**: Para criar e gerenciar containers.
- **[Docker Compose](https://docs.docker.com/compose/)**: Para orquestrar múltiplos containers.
- **[Java 17](https://www.oracle.com/br/java/technologies/downloads/#java17)**: Linguagem de programação usada no backend do projeto.
- **[Git](https://git-scm.com/)**: Controle de versão para clonar o repositório do projeto.

### Adicionais

- **Um editor de texto** como Visual Studio Code ou IntelliJ IDEA para explorar e editar o código.
- **Postman** ou uma ferramenta similar para testar as APIs manualmente, caso deseje fazer isso fora do Swagger.

## Passo a Passo para Executar o Projeto

### 1. Clonar o Repositório

Abra um terminal ou PowerShell e execute o comando para clonar o repositório:

```bash
git clone https://github.com/RafaelJaber/TaskTide.git
```

### 2. Verificar Dependências

Certifique-se de que as seguintes ferramentas estão instaladas em seu sistema:

- **Docker**: [Instruções de instalação do Docker](https://docs.docker.com/get-docker/)
- **Docker Compose**: [Instruções de instalação do Docker Compose](https://docs.docker.com/compose/install/)
- **Java**: [Instruções de instalação do Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)

Verifique as versões instaladas para garantir que tudo está configurado corretamente:

```bash
docker --version
docker-compose --version
java -version
```

### 3. Configuração do Arquivo `.env`

Para configurar o ambiente do seu projeto, você precisará criar um arquivo `.env` na raiz do projeto e definir as seguintes variáveis de ambiente. Este arquivo é utilizado para armazenar informações sensíveis e configurações específicas do ambiente.

### Variáveis de Ambiente

Adicione as seguintes variáveis ao seu arquivo `.env`:

- **`SYSTEM_USER_EMAIL`**: E-mail do usuário do sistema, utilizado para autenticação administrativa.
- **`SYSTEM_USER_PASSWORD`**: Senha do usuário do sistema, utilizado para autenticação administrativa.
- **`EMAIL_SENDER_FRIENDLY_NAME`**: Nome amigável que será exibido como remetente dos e-mails.
- **`EMAIL_SENDER_FROM`**: E-mail do remetente para o envio de mensagens.
- **`SEND_GRID_API_KEY`**: Chave da API do SendGrid para envio de e-mails.
- **`SECRET_KEY`**: Chave secreta utilizada para a criptografia e segurança do sistema. - Deve ser uma chave grande

### Exemplo de Variáveis

Aqui está um exemplo de como configurar as variáveis no arquivo `.env`:

- `SYSTEM_USER_EMAIL="admin@example.com"`
- `SYSTEM_USER_PASSWORD="securePassword123"`
- `EMAIL_SENDER_FRIENDLY_NAME="MyApp Notifications"`
- `EMAIL_SENDER_FROM="no-reply@myapp.com"`
- `SEND_GRID_API_KEY="SG.token-gerado-send-grid"`
- `SECRET_KEY="c6de5b8e65bfe7d2c90d8b6a5fd64e99cd6f7a0d92e1f8b8c8b7e9f4a3d2c1e0"`

### Instruções para Gerar o Token `SEND_GRID_API_KEY`

Para gerar a chave da API do SendGrid, siga os passos abaixo:

1. **Acesse o SendGrid**: Vá para o [site do SendGrid](https://sendgrid.com/) e faça login na sua conta. Se você ainda não tiver uma conta, será necessário criar uma.
2. **Navegue até a Seção de API Keys**: Após fazer login, acesse o painel do SendGrid e vá para "Settings" (Configurações) e depois "API Keys".
3. **Crie uma Nova API Key**:
- Clique no botão "Create API Key" (Criar Chave de API).
- Dê um nome para sua chave e selecione as permissões necessárias.
- Clique em "Create & View" (Criar e Visualizar) para gerar a chave.
4. **Copie a Chave**: Após a criação, você verá a chave gerada. Copie esta chave e cole-a no valor da variável `SEND_GRID_API_KEY` no seu arquivo `.env`.

### Passos para Configuração

1. **Crie o Arquivo `.env`**: Na raiz do seu projeto, crie um arquivo chamado `.env` se ele ainda não existir.
2. **Adicione as Variáveis**: Copie e cole as variáveis acima no arquivo `.env`.
3. **Salve o Arquivo**: Salve as alterações no arquivo `.env`.

Essas configurações são essenciais para o funcionamento correto do projeto e garantem que o sistema possa enviar e-mails e manter a segurança adequada.


### 4. Executar o Script de Construção e Implantação

Dependendo do seu sistema operacional, execute o script apropriado:

### Sistemas Unix

1. No terminal, navegue até o diretório do projeto:
  ```bash
  cd TaskTide
  ```
2. Torne o script `build_and_deploy.sh` executável e execute-o:
  ```bash
  chmod +x build_and_deploy.sh
  ```
3. Execute o `build_and_deploy.sh`:
  ```bash
  ./build_and_deploy.sh 
  ```


### Windows PowerShell - Execução de comandos precisa estar habilitada

1. No PowerShell, navegue até o diretório do projeto:
```powershell
cd TaskTide
```
2. Execute o script `build_and_deploy.ps1`:
```powershell
.\build_and_deploy.ps1
```

### Windows CMD

1. No Prompt de Comando, navegue até o diretório do projeto:
```cmd
cd TaskTide
```
2. Execute o script `build_and_deploy.bat`:
```cmd
build_and_deploy.bat
```

## Acessos e Configurações

### Acesso à Interface do Eureka

Para acessar a interface do Eureka, use o seguinte link:

[http://localhost:8761](http://localhost:8761)

Na página, verifique o item **Instances currently registered with Eureka** para garantir que os 4 microserviços estejam registrados.

### Acesso ao Swagger

Para acessar a interface do Swagger, utilize o seguinte link:

[http://localhost:8000/swagger-ui/index.html#/](http://localhost:8000/swagger-ui/index.html#/)

### Download da Coleção do Insomnia

Para fazer o download da coleção do Insomnia, clique no link abaixo:

[Download da Coleção do Insomnia](https://github.com/RafaelJaber/TaskTide/raw/master/docs/insomnia-collection.json)

Segue abaixo o conteúdo das variáveis de ambiente para a coleção em JSON:

```json
{
	"url": "http://localhost:8000",
	"task-server": "http://localhost:8000",
	"notification-server": "http://localhost:9000"
}
```

## 🚀 Cadastro do Usuário Padrão

Após iniciar o sistema, é necessário cadastrar o usuário padrão que foi especificado nas variáveis de ambiente. Este usuário padrão será utilizado para as operações administrativas iniciais do sistema.

### Instruções para Cadastro do Usuário Padrão

1. **Inicie o Sistema**: Certifique-se de que o sistema está em funcionamento. Isso pode ser feito executando os scripts de build e deploy conforme descrito anteriormente.

2. **Realize o Cadastro do Usuário**: Utilize um cliente HTTP (como Postman, Insomnia, ou cURL) para fazer uma requisição POST para o endpoint responsável pelo cadastro de usuários.

- **Endpoint**: `POST /users`
- **URL Completa**: `http://localhost:8000/users` (ou a URL correspondente ao seu BFF)
- **Método**: POST
- **Headers**:
  - `Content-Type: application/json`
- **Corpo da Requisição**: Envie um JSON com os seguintes dados:

  ```json
  {
    "name": "System",
    "email": "system@system.com",
    "password": "123456",
    "addresses": [],
    "contacts": []
  }
  ```

O `email` e a `password` devem corresponder às variáveis `SYSTEM_USER_EMAIL` e `SYSTEM_USER_PASSWORD` especificadas no arquivo `.env`.

3. **Verifique o Cadastro**: Após enviar a requisição, verifique se o usuário foi cadastrado corretamente no sistema. Você pode fazer isso verificando a resposta da API ou acessando a interface administrativa, se disponível.

Seguir esses passos garantirá que o usuário padrão seja registrado e pronto para usar as funcionalidades administrativas do sistema.

<h2 id="routes">📍 Endpoints da API</h2>

| **Endpoint**                      | **Method** | **Tag**          | **Summary**                           | **Description**                                                             | **Responses**                         |
|-----------------------------------|------------|------------------|---------------------------------------|-----------------------------------------------------------------------------|---------------------------------------|
| `/users`                          | `POST`     | User API         | Create a new user                     | Create a new user with the provided data                                    | 201: User created successfully        |
| `/users/me`                       | `PUT`      | User API         | Update logged-in user's data          | Update the logged-in user's data based on the JWT token                     | 200: User data updated successfully   |
| `/users/me/contacts/{contactId}`  | `PUT`      | User Contact API | Update an existing contact            | Update an existing contact for the logged-in user                           | 200: Contact updated successfully     |
| `/users/me/contacts/{contactId}`  | `DELETE`   | User Contact API | Delete a contact                      | Delete a contact of the logged-in user by contact ID                        | 204: Contact deleted successfully     |
| `/users/me/addresses/{addressId}` | `PUT`      | User Address API | Update an existing address            | Update an existing address for the logged-in user                           | 200: Address updated successfully     |
| `/users/me/addresses/{addressId}` | `DELETE`   | User Address API | Delete an address                     | Delete an address of the logged-in user by address ID                       | 204: Address deleted successfully     |
| `/tasks/{taskId}`                 | `PUT`      | Task API         | Update an existing task               | Update the details of an existing task for the logged-in user               | 200: Task updated successfully        |
| `/tasks/{taskId}`                 | `DELETE`   | Task API         | Delete an existing task               | Delete an existing task for the logged-in user by task ID                   | 204: Task deleted successfully        |
| `/users/me/contacts`              | `GET`      | User Contact API | Find contacts of the logged-in user   | Retrieve the list of contacts for the logged-in user using a JWT token      | 200: Contacts retrieved successfully  |
| `/users/me/contacts`              | `POST`     | User Contact API | Create a new contact                  | Create a new contact for the logged-in user                                 | 201: Contact created successfully     |
| `/users/me/addresses`             | `GET`      | User Address API | Find addresses of the logged-in user  | Retrieve the list of addresses for the logged-in user using a JWT token     | 200: Addresses retrieved successfully |
| `/users/me/addresses`             | `POST`     | User Address API | Create a new address                  | Create a new address for the logged-in user                                 | 201: Address created successfully     |
| `/users/login`                    | `POST`     | User API         | Authenticate user                     | Perform user login and return a JWT token                                   | 200: Login successful                 |
| `/tasks`                          | `POST`     | Task API         | Create a new task                     | Create a new task for the logged-in user                                    | 201: Task created successfully        |
| `/tasks/{taskId}/change-status`   | `PATCH`    | Task API         | Change the status of an existing task | Change the status of a task (e.g., to completed) for the logged-in user     | 200: Task status changed successfully |
| `/users/{email}`                  | `GET`      | User API         | Find user by email                    | Retrieve a user by the provided email                                       | 200: User found                       |
| `/users/{email}`                  | `DELETE`   | User API         | Delete user by email                  | Delete the user with the provided email                                     | 204: User deleted successfully        |
| `/tasks/my`                       | `GET`      | Task API         | Find tasks of the current user        | Retrieve all tasks created by the logged-in user                            | 200: Tasks retrieved successfully     |
| `/tasks/events`                   | `GET`      | Task API         | Find tasks by scheduling date         | Retrieve tasks scheduled within a specific date and time range for the user | 200: Tasks retrieved successfully     |


<h2 id="team">🤝 Colaboradores</h2>

<div style="display: flex; justify-content: space-around; align-items: center; margin-top: 20px;">

  <div style="text-align: center;">
    <a href="https://github.com/rafaeljaber" target="_blank">
      <img src="https://github.com/rafaeljaber.png" width="120px;" alt="Rafael Jáber Profile Picture" style="border-radius: 50%; border: 2px solid #ddd;"/>
      <br>
      <sub>
        <b>Rafael Jáber</b>
      </sub>
    </a>
    <p style="font-style: italic;">Programador</p>
  </div>

  <div style="text-align: center;">
    <a href="https://github.com/angelicaweiler" target="_blank">
      <img src="https://github.com/angelicaweiler.png" width="120px;" alt="Angélica Weiler Profile Picture" style="border-radius: 50%; border: 2px solid #ddd;"/>
      <br>
      <sub>
        <b>Angélica Weiler</b>
      </sub>
    </a>
    <p style="font-style: italic;">Instrutora</p>
  </div>

</div>

<h2 id="thanks">🙏 Agradecimentos</h2>

Gostaria de expressar minha sincera gratidão a todos que contribuíram para a realização deste projeto:

- **Angélica Weiler**: Agradeço pelo suporte e orientação durante o desenvolvimento, além de fornecer insights valiosos que ajudaram a aprimorar o projeto.
- **Equipe do Curso Javanautas**: Pela estruturação do curso e pelos materiais didáticos, que foram fundamentais para o aprendizado e aplicação das tecnologias utilizadas.
- **Comunidade Open Source**: Pelo desenvolvimento e manutenção das ferramentas e frameworks que possibilitaram a construção deste projeto.
- **Você, Leitor**: Agradeço por dedicar seu tempo para explorar este projeto. Espero que ele possa inspirar e auxiliar em seus próprios empreendimentos. Seu interesse e feedback são valiosos para mim.

Sua contribuição e suporte foram essenciais para o sucesso deste projeto. Muito obrigado!


Obrigado por acompanhar e apoiar este trabalho!