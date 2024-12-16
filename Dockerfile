# Użyj oficjalnego obrazu JDK
FROM openjdk:17-jdk-slim

# Ustaw katalog roboczy
WORKDIR /app

# Kopiuj pliki jar z projektu do kontenera
COPY target/*.jar app.jar

# Uruchom aplikację
ENTRYPOINT ["java", "-jar", "app.jar"]
