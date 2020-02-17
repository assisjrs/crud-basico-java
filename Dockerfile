FROM adoptopenjdk:8-jdk-openj9-bionic

EXPOSE 9090

RUN mkdir /app
WORKDIR /app

COPY ./build/libs/*.jar /app/

ENTRYPOINT ["java", "-jar" ,"app.jar"]

