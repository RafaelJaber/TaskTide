FROM openjdk:23-ea-17-jdk-slim

WORKDIR /app

COPY build/libs/user-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/application.jar"]