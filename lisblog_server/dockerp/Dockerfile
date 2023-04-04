FROM openjdk:8-jdk-alpine
MAINTAINER lpf
VOLUME /tmp
ADD blog-0.0.1.jar /blog.jar
#地址映射
CMD ["--server.port=8080"]
#对外暴露端口
EXPOSE 8080
#执行命令
ENTRYPOINT ["java","-jar","/blog.jar"]