FROM openjdk:17-alpine

COPY target/*.jar app.jar

EXPOSE 8090

ENTRYPOINT ["java","-Duser.timezone=Asia/Shanghai","-Dfile.encoding=UTF-8","-jar","/app.jar"]
