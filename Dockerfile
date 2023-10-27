FROM amazoncorretto:19

MAINTAINER CRISTIAN

COPY target/security-0.0.1-SNAPSHOT.jar security-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/security-0.0.1-SNAPSHOT.jar"]"]