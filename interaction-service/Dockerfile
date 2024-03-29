FROM openjdk:17-oracle
VOLUME /tmp
COPY target/*.jar  app.jar
ENTRYPOINT ["java","-jar", "app.jar"]



## Use the official Redis image as a base
#FROM redis:latest
#
#
#
## Command to run when the container starts
#CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]
