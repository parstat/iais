FROM openjdk:11.0.1-jre
ENV JAVA_OPTS ""

VOLUME /tmp
COPY security-api-0.0.1-SNAPSHOT.jar /app/app.jar

RUN sh -c 'touch /app/app.jar'
EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS  -jar /app/app.jar