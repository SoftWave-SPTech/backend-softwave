FROM eclipse-temurin:21-jdk-alpine

# Instalar curl no Alpine
RUN apk add --no-cache curl

VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
