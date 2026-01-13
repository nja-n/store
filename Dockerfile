# Stage 1: Build the Spring Boot JAR
FROM maven:3.8.5-openjdk-25 AS builder
WORKDIR /store
COPY store/ .
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:25-jdk
COPY --from=builder /store/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
