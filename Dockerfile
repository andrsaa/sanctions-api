## Use an official OpenJDK runtime as a parent image
#FROM openjdk:21-jdk-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the Gradle wrapper and settings files
#COPY gradlew /app/gradlew
#COPY gradle /app/gradle
#COPY settings.gradle.kts /app/settings.gradle.kts
#COPY build.gradle.kts /app/build.gradle.kts
#
## Copy all project files
#COPY . /app
#
## Make Gradle wrapper executable
#RUN chmod +x gradlew
#
## Build the project
#RUN ./gradlew build
#
## Copy the build artifacts to the container
#COPY app-main/build/libs/sanctions-main.jar /app/sanctions-main.jar
#
## Make port 8080 available to the world outside this container
#EXPOSE 8080
#
## Run the application
#ENTRYPOINT ["java", "-jar", "/app/sanctions-main.jar"]


# Use an official Tomcat image as a parent image
FROM tomcat:10.1-jdk21

# Set the working directory in the container
WORKDIR /usr/local/tomcat/webapps/

# Copy the build artifacts to the container
COPY build/libs/sanctions-api.war ./sanctions-api.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]