FROM eclipse-temurin:17-jdk

WORKDIR /app

# Install Gradle
RUN apt-get update && apt-get install -y wget unzip
RUN wget https://services.gradle.org/distributions/gradle-7.6-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-7.6-bin.zip
ENV GRADLE_HOME=/opt/gradle/gradle-7.6
ENV PATH=${GRADLE_HOME}/bin:${PATH}

COPY . .

RUN gradle build -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/storyquest-api-0.0.1-SNAPSHOT.jar"]
