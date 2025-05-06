before running microservices, you have to provide environmental variables:
KAFKA_URL
DB_URL
DB_USERNAME
DB_PASSWORD
EXPOSE_PORT (not necessary if running in docker container)

for example (for order microservice):

export KAFKA_URL=127.0.0.1:9092
export DB_URL=jdbc:postgresql://localhost/com.ecommerce.db.order
export DB_USERNAME=postgres
export DB_PASSWORD=postgres

export EXPOSE_PORT=8080


to build docker image you don't have to build .jar files first, all you need to do is:

export DOCKER_DEFAULT_PLATFORM=linux/amd64 (or other platform which works for your OS)
docker build -t <name>-service-image -f <name>-service/Dockerfile .


and later, to run docker image (for order microservice with local db and kafka broker):

docker run -e KAFKA_URL=127.0.0.1:9092 -e DB_URL=jdbc:postgresql://host.docker.internal/com.ecommerce.db.order -e DB_USERNAME=postgres -e DB_PASSWORD=postgres <image_name>
