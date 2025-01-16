# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy build files
COPY target/app.jar gateway.jar

# Expose the port the application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "gateway.jar"]
