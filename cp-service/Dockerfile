FROM openjdk:16

COPY *.jar /app.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","/app.jar","--server.port=80"]