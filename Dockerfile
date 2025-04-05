FROM eclipse-temurin:21-jre-alpine
EXPOSE 8881
ADD target/springBootAuth-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]