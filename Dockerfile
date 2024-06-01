FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/Veterinaria-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_veterinaria.jar
EXPOSE 8093
ENTRYPOINT  ["java","-jar","app_veterinaria.jar"]

