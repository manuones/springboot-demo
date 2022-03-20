FROM maven:3.8.4-jdk-8-slim
ADD ./target/*.jar /app/app.jar


EXPOSE 8080
ENV JAVA_OPTS="-Xmx1024m -Xms512m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS \
-Dfile.encoding=UTF8 -Duser.timezone=GMT+08 \
-jar /app/app.jar"]
