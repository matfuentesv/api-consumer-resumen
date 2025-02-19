# Usa OpenJDK 21
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el JAR generado
COPY target/*.jar app.jar

# Expone el puerto de la app
EXPOSE 9099

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]



