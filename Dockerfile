FROM eclipse-temurin:21-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/totvs-desafio-java-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
