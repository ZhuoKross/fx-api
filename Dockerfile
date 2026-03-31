FROM eclipse-temurin:25.0.2_10-jdk

WORKDIR /app/test/

COPY target/fx_api-0.0.1-SNAPSHOT.jar  fx-api-application.jar

