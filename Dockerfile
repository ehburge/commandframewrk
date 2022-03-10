FROM openjdk:17-alpine

WORKDIR /app
ARG JAR_FILE=./build/libs/thingfrmwrkapp-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 10080

CMD ["java", "-jar", "app.jar"]