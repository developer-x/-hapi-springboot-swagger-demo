# HAPI support for Spring Boot and Swagger

This Project demonstrates how to integrate
HAPI with Spring Boot and Swagger.

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

