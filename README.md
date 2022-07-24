# SIBS Challenger
This is a simple exercise, a simple order manager. You should develop an API where users can create and manage orders. Items can be ordered and orders are automatically fulfilled as soon as the item stock allows it

# Clone Project
```
https://github.com/wendellwoney/SibsChallenger.git
```

# Folder Structure
```
.src/main/java/com.wendellwoney.SibsChallenger
├── configuration 
├── controller
├── dto
├── mail
├── model
├── repository
├── service
```

# Dependencies

* Spring 2.7.2
* Spring JPA (Connection Postgres)
* Spring Mail
* Modal Mapper
* Java 8
* Swagger 2
* Log4j2
* Lombock
* Docker

# Configuring the Project

### Install docker step by step 
```shell
sudo apt update

sudo apt install apt-transport-https ca-certificates curl software-properties-common

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"

sudo apt update

apt-cache policy docker-ce

sudo apt install docker-ce

sudo systemctl status docker

sudo usermod -aG docker ${USER}

su - ${USER}

```

### Configuring properties

Rename ``applicationDEV.properties`` to ``application.properties`

```
#data source
spring.database.driverClassName=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://IP:5430/mydb #ADD YOUR IP
spring.datasource.username=postgres
spring.datasource.password=12345

#jpa
spring.jpa.database=POSTGRESQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.globally_quoted_identifiers=true

#sql
spring.sql.init.platform=postgres

#Server
api.path=/stock
server.port=8080

#Mail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username= #ADD EMAIL SENT TO EMAIL
spring.mail.password= #ADD PASSWORD SENT TO EMAIL
spring.mail.protocol=smtp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.enable=true
spring.mail.default-encoding=UTF-8
spring.mail.properties.mail.smtp.socketFactory.port=465
spring.mail.properties.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
```

### Run project
Access the project root and run ``./start.sh`` file. The file will start the project build and the docker instance

```
dockker-compose down

mvn clean package -DskipTests

dockker-compose build

docker-compose up
```
