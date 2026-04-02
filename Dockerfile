# Use Java 21 (AWS optimized)
FROM amazoncorretto:21-alpine

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]