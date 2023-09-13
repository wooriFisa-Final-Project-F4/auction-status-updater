FROM openjdk:17-jdk
WORKDIR /app
COPY build/libs/auction-status-updater-0.0.1-SNAPSHOT.jar /app/auction-status-updater.jar
CMD ["java", "-jar", "/app/auction-status-updater.jar"]