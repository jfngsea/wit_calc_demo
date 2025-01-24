# WIT Java Demo - João Martins

WIT Software Java Challenge - João Martins
Development of two modules: Rest and Calculator that communicate using Kafka.

## Build
To build all the components' JARs run ```./gradlew build``` in the root dir.

## Run

Before running the Rest and Calculator modules run the compose.yaml file to spin up a Kafka Instance.
After the Kafka instance is up and running each of the components can be executed by running the commands in separate terminals:
- Rest: ```java -jar Rest/build/libs/Rest-0.0.1-SNAPSHOT.jar```
- Calculator ```java -jar Calculator/build/libs/Calculator-0.0.1-SNAPSHOT.jar```

With both components running, the API is available at localhost:8080
