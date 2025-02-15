# BookHub Backend

Dieses Projekt ist ein Spring Boot-basiertes Backend zur Verwaltung von Büchern, Autoren, Verlagen, Genres und Bewertungen. Es verwendet Maven zum Bauen und Docker, um die Anwendung in Containern auszuführen.

## Voraussetzungen

- Java JDK 17 (oder höher)
- Maven 3.x
- Docker (optional, um die Anwendung in einem Container auszuführen)


## Repository klonen

git clone https://github.com/yungceddose/bookhub.git

## Projekt bauen

mvn clean package

## Integrationstests ausführen

mvn clean verify

## Docker-Image erstellen

docker build -t bookhub .

## Docker-Container starten

docker run -p 8080:8080 bookhub

Die Anwendung ist nun unter http://localhost:8080 erreichbar


