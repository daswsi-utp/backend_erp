# Fase de construcción
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# 1. Copia el POM padre primero
COPY pom.xml ./parent-pom.xml

# 2. Instala manualmente el POM padre
RUN mvn install:install-file \
    -Dfile=parent-pom.xml \
    -DgroupId=com.mycompany.app \
    -DartifactId=erpMicroservices \
    -Dversion=1.0-SNAPSHOT \
    -Dpackaging=pom

# 3. Copia el POM del microservicio
COPY microservice-plannig/pom.xml .

# 4. Descarga dependencias
RUN mvn dependency:go-offline -B

# 5. Copia el código fuente del microservicio
COPY microservice-plannig/src ./src

# 6. Construye el proyecto
RUN mvn package -DskipTests

# Fase de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8094
ENTRYPOINT ["java", "-jar", "app.jar"]




