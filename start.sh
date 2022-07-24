dockker-compose down

mvn clean package -DskipTests

dockker-compose build

docker-compose up