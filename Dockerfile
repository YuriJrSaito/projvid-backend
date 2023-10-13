FROM openjdk:17-alpine
VOLUME /tmp
ENV APP_NAME projvid-1.0
COPY ./target/${APP_NAME}.jar /app/${APP_NAME}.jar
ENTRYPOINT ["java","-jar","/app/projvid-1.0.jar"]