FROM amazoncorretto:17

WORKDIR /app

COPY target/cabsbookingapi.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
