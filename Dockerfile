FROM openjdk:17-jdk-slim
WORKDIR /java
COPY target/duty-bot-2-0.0.1-SNAPSHOT.jar /java/dutybot2.jar
CMD ["java","-jar", "dutybot2.jar"]