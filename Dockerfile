FROM openjdk:11-jdk-slim
ARG JAR_VERSION
ARG SERVICE_NAME
COPY wait-for.sh /
COPY target/${SERVICE_NAME}-${JAR_VERSION}-exec.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar", "--spring.profiles.active=docker"]
RUN  apt-get --assume-yes update \
  && apt-get --assume-yes upgrade \
  && apt-get --assume-yes install netcat-openbsd \ 
  && chmod +x /wait-for.sh
