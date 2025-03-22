FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# Use the correct path to gradlew
RUN chmod +x ./storyquest-api/gradlew

# Run gradlew from the correct directory
WORKDIR /app/storyquest-api
RUN ./gradlew build -x test

EXPOSE 8080

# Make sure to use the correct path to the JAR file
CMD ["java", "-jar", "build/libs/storyquest-api-0.0.1-SNAPSHOT.jar"]
