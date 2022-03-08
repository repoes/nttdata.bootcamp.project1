FROM openjdk:8-jdk-alpine
ADD target/*.jar /usr/share/gateway.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar","/usr/share/gateway.jar"]