# SIBS Challenge
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

Rename ``applicationDEV.properties`` to ``application.properties``

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
spring.mail.username= noreplaysibs@gmail.com
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
docker-compose down
docker-compose build
mvn clean package -DskipTests
docker-compose up
```
Will create a container with postgres database named mydb and application access url ``http://localhost:8282/stock``

### Swagger
Access the path with the project running: ``http://localhost:8282/swagger-ui/index.html#/``

![image](https://user-images.githubusercontent.com/29403648/180661774-7bdcb0a5-c34e-4380-bae6-efbe2b3b5f20.png)

### Insominia
To access the collection of endpoints through insomnia, just click on the link below and import
[Insomnia_2022-07-24.zip](https://github.com/wendellwoney/SibsChallenger/files/9176480/Insomnia_2022-07-24.zip)
![image](https://user-images.githubusercontent.com/29403648/180661937-48fb455c-e33b-4586-826c-d8fa7156813b.png)

To install on ubuntu
```shell
sudo snap install insomnia
```

### Log Files
The log files are divided as follows
```
stock-debug.log   #Aplication Debug
stock-error.log   #Orders completed, stock movements, email sent, applications info
stock-warning.log #Application warning
stock-error.log   #Application warning
```

### Access log files

To access the log files it is necessary to access the ``app-springboot docker`` container using the following command
```shell
docker exec -it app-springboot /bin/sh

/ #
cd logs
ls
stock-debug.log    stock-error.log    stock-info.log     stock-warning.log
```

To view the contents of the file
```shell
cat stock-info.log

2022-07-24 18:03:07 INFO - Starting SibsChallengerApplication v1 using Java 1.8.0_212 on 0559dc4863fd with PID 1 (/sibs.jar started by root in /)
2022-07-24 18:03:07 INFO - No active profile set, falling back to 1 default profile: "default"
2022-07-24 18:03:11 INFO - Started SibsChallengerApplication in 4.309 seconds (JVM running for 4.941)

```

