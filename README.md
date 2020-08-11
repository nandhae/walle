<b>Wall-E : A Spring Boot Demo Application</b>

###Prerequisites:

    - JDK 11
    - Gradle 6.3

--Create user and database
```sh
    CREATE USER user WITH PASSWORD 'password';
    CREATE DATABASE todo OWNER promouser;
```
--To build
```sh
$ ./gradlew clean build
```

--To run migrations
```sh
$ ./gradlew flywayMigrate -i
```

--To start the server
```sh
$ ./gradlew clean bootRun
```

--To run the tests
```sh
$ ./gradlew test
```gs
