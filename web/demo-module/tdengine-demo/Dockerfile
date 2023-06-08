FROM openjdk:8-jdk

COPY app.jar app.jar
COPY TDengine-client-2.4.0.5-Linux-x64.tar.gz /usr/local/taosd/

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && cd /usr/local/taosd \
    && tar -xzvf TDengine-client-2.4.0.5-Linux-x64.tar.gz \
    && cd /usr/local/taosd/TDengine-client-2.4.0.5 \
    && ./install_client.sh

COPY taos.cfg /etc/taos/

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=Asia/Shanghai","-Dfile.encoding=UTF-8","-jar","/app.jar"]