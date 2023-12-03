ARG APP_JAR=stock-tracking-system-0.0.1-SNAPSHOT.jar
FROM gradle:8.5-jdk21 AS build
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test

FROM openjdk:21
ARG APP_JAR
ENV APP_JAR_ENV=$APP_JAR
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/${APP_JAR_ENV} /app/${APP_JAR_ENV}
WORKDIR /app
EXPOSE 8081
ENTRYPOINT java -jar /app/${APP_JAR_ENV}
