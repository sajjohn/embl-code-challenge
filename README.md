# Embl-code-challenge
The application has been built using Spring Boot. In Dev mode The API documentation is available in the following location (http://localhost:8082/emblservice/swagger-ui.html).

To start the application in foreground run the following command -:

mvn spring-boot:run



# Assumptions

The below assumptions have been made while development -:

* API is being directly exposed to endusers without any API gateway.
* API is unsecured with out any Authentication or Authorization framework.
* The entities will be persisited using an In-Memory H2 DB.
* The project uses Lombok for getter setter generation, please download lombok plugins to set up workspace(Eclipse/ IntelliJ etc.)
* Basic Validation has been provided on the Person entity using Bean validator framework.
* Error message will be thrown to user if the entity being searched doesn't exist in DB.
* The /getPersons endpoint allows to filter results based on certain properties passed in request parameter.
* A custom error object has been created to display error messages in case of exception.
* Integration Tests have been written instead of Unit test as integraton test covers the whole codebase.



# About the Challenge

It was very enjoyable working on the assignment . Have tried to incorporate many best practices  to optimise the application.

I hope my solution serves the purpose & eagerly looking forward to receive your feedback.





