# Rest Assured Airport Gap API Tests

This project contains a set of API tests for the Airport Gap API, written using Rest-Assured.

## Description

The tests cover various endpoints of the Airport Gap API, including:
*   Fetching airports
*   Fetching a single airport by ID
*   Creating new airports
*   Managing favorite airports
*   API authentication and token management

The tests are designed to verify:
*   Correct status codes for valid and invalid requests
*   Response body content and structure against JSON schemas
*   Response headers
*   Response times

## Prerequisites

This is a Maven project, so you will need to have Java and Maven installed on your system.

1.  **Java:** Make sure you have Java JDK 18 or higher installed.
2.  **Maven:** Install Maven by following the instructions on the [official Maven website](https://maven.apache.org/install.html).

## Environment Setup

The tests require a base URL, an authentication token, an email, and a password. These are managed by the `ConfigReader.java` class, which reads from a `configuration.properties` file located in `src/test/resources`. Before running the tests, you need to create this file and add the following properties:

    ```properties
    base_url=<your_api_base_url>
    token=<your_api_token>
    email=<your_email>
    password=<your_password>
    ```
    > **Tip:** You can find the credentials in the `src/test/resources/configuration.properties` file.


## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Built With

*   [Maven](https://maven.apache.org/) - Dependency Management
*   [REST-assured](https://rest-assured.io/) - API Testing Framework
*   [TestNG](https://testng.org/doc/) - Testing Framework
*   [Jackson](https://github.com/FasterXML/jackson) - JSON Processor
*   [Hamcrest](http://hamcrest.org/JavaHamcrest/) - Matcher Objects
*   [JSON Schema Validator](https://github.com/rest-assured/rest-assured/wiki/Usage#json-schema-validation) - JSON Schema Validator for REST Assured

## How to Run the Tests
To run the tests, open a terminal or command prompt, navigate to the root directory of the project, and execute the following Maven command:

```bash
mvn test
```

This command will compile the project, download the dependencies, and run the tests using the Maven Surefire plugin. Test reports will be generated in the `target/surefire-reports` directory.

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