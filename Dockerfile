# Этап 1: сборка приложения
FROM maven:3.9-eclipse-temurin-17 as builder

WORKDIR /build

# Кэшируем зависимости отдельно
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: минимальный образ для запуска
FROM openjdk:17-jdk-slim

WORKDIR /app

# Копируем собранный JAR-файл из предыдущего этапа
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
