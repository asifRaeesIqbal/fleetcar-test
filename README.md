# Fleetcar - tombola Application

Is a basic spring boot application. It follows a layered architecture. built using the following:

* Spring-boot
* Java 8
* Maven

## Assumptions:

Date and time are not relevant and not required. Also the player information is not stored.

Thread safety is also not required for now. Thus using a basic H2 database

History is also not required.

Full documentation or any standard of coding style is totally dependant on the developer.

## Running the application

The application can be started using jar file (located in target folder in source). IF NOT source code will need to be compiled and jar built using Maven command (mvn install). Once the JAR file is found or built, it can be run using the following command.

java -jar fleetcarXXX.jar

The application should be available on localhost port 8080

the swagger for the applciation is also available on :

http://localhost:8080/fleetcar/swagger-ui.html
