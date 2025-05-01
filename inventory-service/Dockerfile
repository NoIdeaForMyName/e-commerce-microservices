FROM --platform=linux/amd64 maven:3.9.9-eclipse-temurin-21-alpine
WORKDIR /build
COPY common ./common
COPY inventory-service ./inventory-service
WORKDIR /build/common
RUN mvn clean package -DskipTests
RUN mvn clean install
WORKDIR /build/inventory-service
RUN mvn clean package -DskipTests


FROM --platform=linux/amd64 eclipse-temurin:17-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8082
COPY --from=0 /build/inventory-service/target/*.jar ./inventory-service.jar
ENTRYPOINT ["java", "-jar", "inventory-service.jar"]
