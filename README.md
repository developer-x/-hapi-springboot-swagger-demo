# HAPI support for Spring Boot and Swagger

This Project demonstrates how to integrate
[HAPI](https://github.com/hapifhir/hapi-fhir) with 
[Spring Boot](https://spring.io/projects/spring-boot) and 
[Swagger](https://swagger.io/tools/open-source/open-source-integrations/).

The problem with integration is that Spring Boot and Swagger 
uses Jackson for serialization and inspection.
And because of the complexities of the HAPI Datamodel, 
Jackson is unable to inspect or serialize/deserialize.

To address these shortcomings, do the following:

* [Create a MessageConverter](src/main/java/developerx/springframework/HapiHttpMessageConverter.java)
to use HAPI's serialization/deserialization library
instead of Jackson for HAPI Resources.
* [Create a Swagger ModelConverter](src/main/java/developerx/swagger/HapiSwaggerSupport.java)
to direct swagger to only inspect non-private fields
and truncate less relevant fields
* Since HAPI's models are complex, provide [sensible
Swagger default values](src/main/java/developerx/swagger/PatientDocumentation.java)

**Note:** Collection of HAPI Resources should use the [Bundle Resource](src/main/java/developerx/hapidemo/ConditionController.java#L45) rather than return a Collection type.

## Running the Demo
From the command line, issue `./gradlew bootRun`.
Once the application is running, open your browser to
http://localhost:8080/swagger-ui.html
