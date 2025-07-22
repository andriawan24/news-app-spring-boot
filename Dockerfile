# Stage 1: Build the application using maven
FROM maven:3.9.11-eclipse-temurin-24-alpine AS build

# Set working directory to /app
WORKDIR /app

# Copy pom.xml file to download dependencies (for caching)
COPY pom.xml .

RUN mvn dependency:go-offline

# Copy all source code
COPY src ./src

# Package the application, skipping tests
RUN mvn package -DskipTests

# Stage 2: Create the final, minimal runtime image
FROM eclipse-temurin:24-jre-ubi9-minimal

# Set workdir to app
WORKDIR /app

# Find jar file on target folder
ARG JAR_FILE=target/*.jar

# Copy build jar from the previous stage
COPY --from=build /app/${JAR_FILE} app.jar

# Expose port 8080 for running the application
EXPOSE 8080

# Command to run the project
ENTRYPOINT ["java", "-jar", "app.jar"]

