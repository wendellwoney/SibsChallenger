docker-compose down
docker-compose build
mvn clean package -DskipTests
docker-compose up