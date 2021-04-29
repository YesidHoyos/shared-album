FROM openjdk:8-jre-alpine
RUN mkdir -p /usr/src/apps/woloxgram/sharedalbum
COPY target/shared-album-0.0.1-SNAPSHOT.jar /usr/src/apps/woloxgram/sharedalbum
WORKDIR /usr/src/apps/woloxgram/sharedalbum
ENV MONGO_HOST_NAME=172.168.0.20
CMD ["nohup", "java", "-jar", "shared-album-0.0.1-SNAPSHOT.jar", "&"]