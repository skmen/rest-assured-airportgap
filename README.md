# Rest Assured Airport Gap API Tests

This project provides a comprehensive set of API tests for the Airport Gap API, utilizing Rest-Assured for robust and maintainable automated tests.

## Description

The tests cover the core functionalities of the Airport Gap API, ensuring the reliability and correctness of its endpoints. The primary areas of focus include:

*   **Airports:** Fetching a list of all airports and retrieving individual airports by their ID.
*   **Airport Distance:** Calculating the distance between two airports.
*   **Favorites:** Retrieving the list of favorite airports for a user.
*   **Authentication:** Obtaining authentication tokens.

The tests are designed to validate various aspects of the API's responses, such as:

*   **Status Codes:** Verifying the correctness of HTTP status codes for both successful and unsuccessful requests.
*   **Response Body:** Ensuring the response payloads are well-formed and adhere to the expected JSON schemas.
*   **Response Headers:** Validating critical response headers.
*   **Response Times:** Measuring and asserting that response times are within acceptable limits.

## Prerequisites

This is a Maven project, so you will need to have Java and Maven installed on your system.

1.  **Java:** Make sure you have Java JDK 18 or higher installed.
2.  **Maven:** Install Maven by following the instructions on the [official Maven website](https://maven.apache.org/install.html).

## Environment Setup

The tests require configuration for the base URL and authentication credentials. This is managed by the `ConfigReader.java` class, which reads from the `environments.properties` file located in `src/test/resources/config`.

Before running the tests, you need to create this file and add the following properties:

```properties
base_url=<your_api_base_url>
token=<your_api_token>
email=<your_email>
password=<your_password>
```

*   `base_url`: The base URL of the Airport Gap API.
*   `token`: An authentication token for accessing protected endpoints.
*   `email`: The email address associated with the account.
*   `password`: The password for the account.

> **Tip:** You can find the credentials in the `src/test/resources/config/environments.properties` file.

## Project Structure

```
.
├── pom.xml
├── src
│   ├── main
│   └── test
│       ├── java
│       │   ├── api_services
│       │   ├── models
│       │   ├── tests
│       │   └── utils
│       └── resources
│           ├── config
│           └── data
└── target
```

*   `pom.xml`: The Project Object Model file, which defines the project's dependencies, plugins, and build settings.
*   `src/test/java/api_services`: Contains classes that encapsulate the logic for interacting with the different API endpoints.
*   `src/test/java/models`: Contains Plain Old Java Objects (POJOs) that represent the data structures used in the API.
*   `src/test/java/tests`: Contains the TestNG test classes.
*   `src/test/java/utils`: Contains utility classes, such as the base test setup and the configuration reader.
*   `src/test/resources/config`: Contains configuration files, such as `environments.properties`.
*   `src/test/resources/data`: Contains data files used by the tests, such as JSON schemas.

## How to Run the Tests

To run the tests, open a terminal or command prompt, navigate to the root directory of the project, and execute the following Maven command:

```bash
mvn test
```

This command will compile the project, download the necessary dependencies, and run the tests using the Maven Surefire plugin. Test reports will be generated in the `target/surefire-reports` directory.

### Running Individual Tests

You can also run a specific test class or a single test method.

To run a specific test class, use the following command:

```bash
mvn -Dtest=YourTestClassName test
```

Replace `YourTestClassName` with the name of the test class you want to run (e.g., `GetAirportsTests`).

To run a single test method within a class, use this command:

```bash
mvn -Dtest=YourTestClassName#yourTestMethodName test
```

Replace `YourTestClassName` with the name of the test class and `yourTestMethodName` with the name of the test method (e.g., `GetAirportsTests#VerifyValidGetAirportsPathReturns200`).

## Built With

*   [Maven](https://maven.apache.org/) - Dependency Management
*   [REST-assured](https://rest-assured.io/) - API Testing Framework
*   [TestNG](https://testng.org/doc/) - Testing Framework
*   [Jackson](https://github.com/FasterXML/jackson) - JSON Processor
*   [Hamcrest](http://hamcrest.org/JavaHamcrest/) - Matcher Objects
*   [JSON Schema Validator](https://github.com/rest-assured/rest-assured/wiki/Usage#json-schema-validation) - JSON Schema Validator for REST Assured
