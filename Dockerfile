# Etapa 1: Compilación con Maven y JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY dominio/pom.xml dominio/
COPY core/pom.xml core/
COPY aplicacion/pom.xml aplicacion/
COPY infraestructura/pom.xml infraestructura/
COPY presentacion/pom.xml presentacion/

COPY dominio/src dominio/src
COPY core/src core/src
COPY aplicacion/src aplicacion/src
COPY infraestructura/src infraestructura/src
COPY presentacion/src presentacion/src

RUN mvn clean package -DskipTests

# Etapa 2: Ejecución ligera con JRE 17
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/presentacion/target/presentacion-1.0.0.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

