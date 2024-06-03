FROM openjdk:17

COPY "./target/Cine-Parcial-0.0.1-SNAPSHOT.jar" "app.jar"


EXPOSE 8105

ENTRYPOINT [ "java", "-jar", "app.jar" ]