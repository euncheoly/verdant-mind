# Stage 1: Build the JAR using Gradle
FROM gradle:8.7-jdk17 AS build
WORKDIR /home/gradle/project

# Copy only the configuration files first (helps with Docker caching)
COPY build.gradle settings.gradle ./
# Copy the source code
COPY src ./src

# Build the application, skipping tests here as we will run them in Jenkins
RUN gradle clean bootJar -x test

# Stage 2: Create the lightweight runtime image
FROM amazoncorretto:17-alpine
WORKDIR /app

# Gradle's default output for the bootJar task is in build/libs/
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]