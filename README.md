# lekker-sa-kos

## Libraries used:
* Spring Boot 1.4.2 
* log4j2 (via Spring Boot)
* JUnit4 (via Spring Boot)
* Mockito (via Spring Boot)
* JDK 1.7

## Tools used:
* Maven 3.*
* Tomcat (embedded via Maven using Spring Boot)

## DB Used:
* MySQL 5.7

## Setting up & running the project:
* Restore the dev database dump file to your local MySQL server.
** Location of dump file: `<project_path>/database/`
* Change the url and login credentials for your restored database in `<project_path>/src/main/resources/application.properties`
* Download project libraries and jars via Maven.
* Run the project via the embedded Tomcat by using the mvn command: `mvn spring-boot:run`
** Api Url: http://localhost:8080/api/dishes
