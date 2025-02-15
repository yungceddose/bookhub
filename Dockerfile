# Wähle ein Basis-Image aus
FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar
# Setze das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiere das JAR-File der Anwendung in das Image
COPY ./target/bookhub-0.0.1-SNAPSHOT.jar app.jar

# Exponiere den Standardport, auf dem Spring Boot läuft


# Führe die JAR-Datei aus
ENTRYPOINT ["java", "-jar", "app.jar"]
