FROM maven:3.9.9-eclipse-temurin-21-alpine
WORKDIR /build
COPY common ./common
COPY client-service ./client-service
WORKDIR /build/common
RUN mvn clean package -DskipTests
RUN mvn clean install
WORKDIR /build/client-service
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8081
COPY --from=0 /build/client-service/target/*.jar ./client-service.jar
ENTRYPOINT ["java", "-jar", "client-service.jar"]
