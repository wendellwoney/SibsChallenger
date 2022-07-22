FROM openjdk:8-jdk-alpine
LABEL maintainer="reachansari"
VOLUME /tmp
EXPOSE 8181
ADD target/sibs.jar sibs.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sibs.jar"]