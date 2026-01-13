# -------- BUILD STAGE --------
FROM maven:3.9.11-eclipse-temurin-25-alpine AS builder
WORKDIR /build

# Copy pom and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests


# -------- RUNTIME STAGE --------

FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy jar from builder
COPY --from=builder /build/target/*.jar app.jar

# Eureka default port
EXPOSE 8761

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
