FROM openjdk:8-jdk-alpine
ADD target/*.jar /usr/share/config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar","/usr/share/config-server.jar"]