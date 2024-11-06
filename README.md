# Iberdrola Tech4Techies Day

## Create the project

To create the project with the same dependencies, use [Spring Initializr](
https://start.spring.io/#!type=gradle-project&language=java&platformVersion=3.3.5&packaging=jar&jvmVersion=21&groupId=com.example&artifactId=demo&name=demo&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.demo&dependencies=web,data-jpa,native,actuator,postgresql,testcontainers,docker-compose,flyway)


## Build and run

### Fatjar

```shell
./gradlew bootJar

java -jar build/libs/iberdrola-0.0.1-SNAPSHOT.jar
```

### GraalVM Native Image

```shell
sdk use java 21.0.2-graalce
./gradlew nativeBuild

./build/native/nativeCompile/iberdrola
```

## Running from terminal

```shell
docker run -it --rm -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=demo postgres:16.3
```

```shell
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/demo
export SPRING_DATASOURCE_USERNAME=user
export SPRING_DATASOURCE_PASSWORD=secret
```
