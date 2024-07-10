FROM ubuntu:latest AS build

# Atualiza o sistema e instala o JDK 17 e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho para /app
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml /app/

# Copia o código-fonte para o diretório de trabalho
COPY src /app/src

# Executa o comando Maven para construir o projeto
RUN mvn clean install

FROM openjdk:17-jdk-slim

# Exponha a porta 8080
EXPOSE 8080

# Copia o JAR construído do estágio de construção anterior
COPY --from=build /app/target/api-delivery-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
