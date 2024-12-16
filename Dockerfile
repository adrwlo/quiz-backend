# Użyj obrazu z JDK jako bazowego
FROM openjdk:17-jdk-slim

# Instalacja narzędzi systemowych oraz Maven
RUN apt-get update && apt-get install -y maven

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj plik pom.xml (Maven) oraz źródła aplikacji
COPY pom.xml ./
COPY src ./src

# Budowa projektu za pomocą Maven
RUN mvn clean package -DskipTests

# Skopiuj wygenerowany plik JAR do obrazu
COPY target/*.jar app.jar

# Uruchom aplikację
CMD ["java", "-jar", "app.jar"]