FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/sibs.jar sibs.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sibs.jar"]