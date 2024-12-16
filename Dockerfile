# Użyj obrazu z JDK jako bazowego
FROM openjdk:17-jdk-slim

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj plik konfiguracyjny Maven i resztę źródeł (potrzebne do budowy aplikacji)
COPY pom.xml ./
COPY src ./src

# Uruchom budowę projektu Maven i wygeneruj plik JAR
RUN apt-get update && apt-get install -y maven && \
    mvn clean package -DskipTests

# Skopiuj wygenerowany plik JAR do obrazu
COPY target/*.jar app.jar

# Uruchom aplikację
CMD ["java", "-jar", "app.jar"]