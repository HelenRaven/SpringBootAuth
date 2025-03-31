FROM eclipse-temurin:21-jre-alpine

EXPOSE 8888

COPY target/springBootAuth-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
