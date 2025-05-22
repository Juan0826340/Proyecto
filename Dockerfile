FROM openjdk:21
COPY "./target/Proyecto2-1.jar" "app.jar"
EXPOSE 8124
ENTRYPOINT [ "java", "-jar", "app.jar" ]
