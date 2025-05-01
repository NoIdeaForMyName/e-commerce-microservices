FROM maven:3.9.9-eclipse-temurin-21-alpine
WORKDIR /build
COPY common ./common
COPY shipping-service ./shipping-service
WORKDIR /build/common
RUN mvn clean package -DskipTests
RUN mvn clean install
WORKDIR /build/shipping-service
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8084
COPY --from=0 /build/shipping-service/target/*.jar ./shipping-service.jar
ENTRYPOINT ["java", "-jar", "shipping-service.jar"]
