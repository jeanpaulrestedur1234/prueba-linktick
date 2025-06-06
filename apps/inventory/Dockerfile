# Usa una imagen base de Maven con OpenJDK
FROM maven:3.8.5-openjdk-17 AS builder

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto al contenedor
COPY . .

# Construye el proyecto y omite los tests (si es necesario)
RUN mvn clean package -DskipTests

# Etapa final para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo .jar generado desde la etapa builder
COPY --from=builder /app/target/*.jar /app/app.jar

# Expone el puerto que Spring Boot usará
EXPOSE 8080

# Comando para ejecutar el archivo .jar
CMD ["java", "-jar", "/app/app.jar"]
