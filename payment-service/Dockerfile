FROM --platform=linux/amd64 maven:3.9.9-eclipse-temurin-21-alpine
WORKDIR /build
COPY common ./common
COPY payment-service ./payment-service
WORKDIR /build/common
RUN mvn clean package -DskipTests
RUN mvn clean install
WORKDIR /build/payment-service
RUN mvn clean package -DskipTests


FROM --platform=linux/amd64 eclipse-temurin:17-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8083
COPY --from=0 /build/payment-service/target/*.jar ./payment-service.jar
ENTRYPOINT ["java", "-jar", "payment-service.jar"]
