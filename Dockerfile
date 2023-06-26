FROM openjdk:21-ea-17-slim-buster

COPY target/*.jar /a/demo.jar

CMD ["java", "-jar", "/a/demo.jar"]
