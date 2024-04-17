FROM openjdk:20
EXPOSE 8080
RUN mkdir /src
RUN mkdir /src/main
RUN mkdir /app
COPY target/quiz-backend-0.0.1-SNAPSHOT.jar /app/quiz-backend.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","quiz-backend.jar"]

