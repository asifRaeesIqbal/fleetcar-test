# Fleetcar - tombola Application

Is a basic spring boot application. It follows a layered architecture. built using the following:

* Spring-boot
* Java 8
* H2
* Maven

## Assumptions:

The app follows a traditional style of tombola, where you purchase tickets and then a ticket is drawn by someone. 

Date and time are not relevant and not required. Also the player information is not stored.

Thread safety is also not required for now. Thus using a basic H2 database

History is also not required.

Full documentation or any standard of coding style is totally dependant on the developer.

The react UI is not fully comprehensive, and lacks some functionality to make it a complete app.

## Running the application

The application can be started using jar file (located in target folder in source). IF NOT source code will need to be compiled and jar built using Maven command (mvn install). Once the JAR file is found or built, it can be run using the following command.

java -jar fleetcarXXX.jar

The application should be available on localhost port 8080

the swagger for the applciation is also available on :

http://localhost:8080/fleetcar/swagger-ui.html


## React front end 

There is a small front end for this, all though not comprehensive, it can be used in conjuction with this.
If the react app is started it will work with this as long as the the port for the app is 8080.
