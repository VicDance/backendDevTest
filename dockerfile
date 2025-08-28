FROM node:20 AS bundler
WORKDIR /app
COPY similarProducts.yaml .
COPY existingApis.yaml .
RUN npm install -g @apidevtools/swagger-cli

COPY api-root.yaml .

RUN swagger-cli bundle api-root.yaml --outfile openapi.yaml --type yaml

FROM openjdk:17-jdk-slim AS builder
WORKDIR /app
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/7.0.0/openapi-generator-cli-7.0.0.jar -O /openapi-generator-cli.jar

COPY --from=bundler /app/openapi.yaml .

RUN java -jar /openapi-generator-cli.jar generate \
    -i openapi.yaml \
    -g spring \
    -o generated-resources \
    --api-package org.backendDevTest.infra.api \
    --model-package org.backendDevTest.infra.model

FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /src
COPY --from=builder /app/generated-resources .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /src/target/*.jar backendDevTest.jar
CMD ["java", "-jar", "backendDevTest.jar"]
