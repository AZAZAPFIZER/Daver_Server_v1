FROM openjdk:17
COPY build/libs/Server_v1-0.0.1-SNAPSHOT-aot.jar app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/app.jar","-Duser.timezone=Asia/Seoul"]