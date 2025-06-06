# Etapa de build
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

# Copiar solo los archivos necesarios para instalar dependencias y cachear las capas de Maven
COPY pom.xml .
COPY src ./src

# Compilar y empaquetar sin pruebas
RUN mvn clean package -DskipTests

# Etapa final: runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el jar generado de la etapa builder
COPY --from=builder /app/target/*.jar app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
