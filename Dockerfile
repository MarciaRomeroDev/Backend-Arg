
FROM amazoncorretto:19-alpine-jdk



COPY  target/API-0.0.1-SNAPSHOT app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
