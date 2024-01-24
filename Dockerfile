
# Fetching latest version of Java
FROM openjdk:20-jdk-slim

# Install dnsutils (includes dig and nslookup)
RUN apt-get update && apt-get install -y dnsutils

# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/microservice1-auth-0.0.1-SNAPSHOT.jar /app

# Exposing port 8081
EXPOSE 8082

# Starting the application
CMD ["java" , "-jar" , "microservice1-auth-0.0.1-SNAPSHOT.jar"]



