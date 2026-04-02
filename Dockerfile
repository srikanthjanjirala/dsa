# Use Java 21 runtime
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file from target folder
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]