
FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar account-0.0.1.jar
EXPOSE 8080
CMD java -jar /account-0.0.1.jar