FROM --platform=linux/amd64 maven:3.9.9-eclipse-temurin-21-alpine
WORKDIR /build
COPY common ./common
COPY order-service ./order-service
WORKDIR /build/common
RUN mvn clean package -DskipTests
RUN mvn clean install
WORKDIR /build/order-service
RUN mvn clean package -DskipTests


FROM --platform=linux/amd64 eclipse-temurin:17-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8080
COPY --from=0 /build/order-service/target/*.jar ./order-service.jar
ENTRYPOINT ["java", "-jar", "order-service.jar"]
