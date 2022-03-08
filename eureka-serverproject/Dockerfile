FROM openjdk:8-jdk-alpine
ADD target/*.jar /usr/share/eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar","/usr/share/eureka-server.jar"]