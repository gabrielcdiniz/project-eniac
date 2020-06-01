# Eniac Project Backend

# Stack

* SpringBoot v2.2.6.RELEASE
    * https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
    * https://docs.spring.io/spring-boot/docs/current/reference/html/

* Spring Data JPA
    * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

* Spring Fox Swagger  
    * https://springfox.github.io/springfox/docs/current/

* H2 In-Memory Database
    * http://www.h2database.com/html/quickstart.html
    * http://www.h2database.com/html/cheatSheet.html
    
* MySQL SGBD
    * https://dev.mysql.com/doc/refman/5.7/en/

### Documentation
Swagger docs at http://localhost:8080

### H2 Console
If the test profile is enabled you can access h2 console just accessing a url 
http://localhost:8080/h2-console/

#### Configurations
```
Saved Settings: Generic H2 (Embedded)

Setting Name: Generic H2 (Embedded)

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:eniac

username: eniac

password: eniac
```

## How to execute me

### Build
```
$ ./gradlew clean build
```

### Launch MySQL docker container 

After build me launch MySQL docker container using docker-compose
```
$ docker-compose up -d
```

And check 
```
$ docker-compose ps

         Name                      Command             State                 Ports              
------------------------------------------------------------------------------------------------
eniacproject_dbeniac_1   docker-entrypoint.sh mysqld   Up      0.0.0.0:3306->3306/tcp, 33060/tcp
```

### Launch application
```
$ ./gradlew clean bootRun
```

### How to stop me

Use a simple CTRL+C to stop bootRun proccess and

```
$ docker-compose down
```

To stop docker-container
