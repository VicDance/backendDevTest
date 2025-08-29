FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY *.yaml .
COPY src ./src

RUN mvn clean package spring-boot:repackage -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/*.jar backendDevTest.jar

EXPOSE 5000

CMD ["java", "-jar", "backendDevTest.jar", "--server.port=5000", "--server.address=0.0.0.0"]
