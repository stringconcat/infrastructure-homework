FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY build/libs/*.jar people-app.jar
ENTRYPOINT ["java", "-jar", "/people-app.jar"]