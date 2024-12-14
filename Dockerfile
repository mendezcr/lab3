# Usa una imagen base de Java 17 con JDK
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor

COPY target/mascota_crud-0.0.1-SNAPSHOT.jar /app/mascotas.jar

# Expone el puerto 8080 para acceder a la aplicación desde fuera del contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/mascotas.jar"]