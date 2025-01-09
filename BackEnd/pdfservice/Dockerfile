# Use an official JDK runtime as a parent image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the project's jar file to the container
COPY target/*.jar app.jar

# Expose the port that the app runs on
EXPOSE 8084

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
