FROM openjdk:8-jdk-alpine
ADD target/*.jar /usr/share/project1.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar","/usr/share/project1.jar"]