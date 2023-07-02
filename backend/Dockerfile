# Use an official Java runtime as a parent image
FROM openjdk:11-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR into the container
COPY /build/libs/backend-0.0.1-SNAPSHOT.jar /app

# Make port 8090 available to the world outside this container
EXPOSE 8090

# Run the JAR file
CMD ["java", "-jar", "/app/backend-0.0.1-SNAPSHOT.jar"]