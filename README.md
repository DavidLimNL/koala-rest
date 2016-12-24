# Koala REST

Searches for fuel stations within a predefined distance

For example:

/api/koala/fuel/by-location?location=52.091167,4.342671&distance=90kilometers

## Libraries used:
* Spring Boot 1.4.2 
* log4j2 (via Spring Boot)
* JUnit4 (via Spring Boot)
* Mockito (via Spring Boot)
* JDK 1.8

## Tools used:
* Maven 3.*
* Tomcat (embedded via Maven using Spring Boot)

## DB Used:
* MongoDB

## Setting up & running the project:
* Restore the dev database dump file to your local MySQL server.
** Location of dump file: `<project_path>/database/`
* Change the url and login credentials for your restored database in `<project_path>/src/main/resources/application.properties`
* Download project libraries and jars via Maven.
* Run the project via the embedded Tomcat by using the mvn command: `mvn spring-boot:run`
** Api Url: http://localhost:8080/api/dishes
