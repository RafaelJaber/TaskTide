FROM openjdk:23-ea-17-jdk-slim

WORKDIR /app

COPY build/libs/naming-server-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8761

CMD ["java", "-jar", "/app/application.jar"]