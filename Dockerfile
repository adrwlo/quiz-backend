# Użyj obrazu JDK jako bazowego
FROM openjdk:17-jdk-slim

# Instalacja narzędzi systemowych oraz Maven
RUN apt-get update && apt-get install -y maven

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj plik pom.xml i pobierz zależności Mavena
COPY pom.xml ./
RUN mvn dependency:go-offline

# Skopiuj resztę kodu projektu
COPY src ./src

# Zbuduj projekt i wygeneruj plik JAR
RUN mvn clean package -DskipTests

# Debugowanie: pokaż zawartość katalogu `target/`
RUN ls -la target/

# Skopiuj plik JAR z katalogu `target/` do obrazu Dockera
RUN cp target/*.jar app.jar

# Debugowanie: upewnij się, że plik `app.jar` istnieje
RUN ls -la app.jar

# Uruchom aplikację
CMD ["java", "-jar", "app.jar"]