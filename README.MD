# Selenium Base Framework

This repository provides a base framework for creating and running Selenium-based automated tests. It includes essential components and configurations to jumpstart test automation project.

---

## Initial Structure

The framework is designed with a modular and scalable structure, supporting the following features:

### Key Features:
- **JUnit 5**: Integrated for writing and running tests with modern annotations and improved parallel execution.
- **Selenium WebDriver**: A robust API for browser automation.
- **Page Object Pattern**: Promotes reusability, maintainability, and readability by separating page elements and interactions into their own classes.
- **Configuration Manager**: Manages configuration settings through property files for better environment and test management.
- **Driver Management**:
   - **Driver Manager**: Implements the Singleton pattern to manage the lifecycle of the WebDriver instance.
   - **Driver Factory**: Simplifies the creation of WebDriver instances with different configurations.
- **Logback**: Configured as the default logger to handle log messages at various levels (INFO, DEBUG, ERROR).
- **AssertJ**: A fluent assertion library for better readability and customization of test validations.
- **Selenium Page Factory**: Utilizes Selenium's default `@FindBy` annotations for easier element location.
- **Parallel Test Execution**: JUnit 5 support for running tests in parallel on a per-class level for faster execution.
- **Allure Reporting**: Default Allure reporting allowing to generate reports with comprehensive information based on test results.
---

### Additional Notes:

The framework provides examples for implementing custom waiters and JavaScript-based interactions.

---

## How to run Tests:
1. Clone the repository:
`   git clone <repository-url>
   cd <repository-directory>`
2. Run tests using Gradle:
`   ./gradlew clean test allureServe`

## Future Improvements:
1. Add support of DB interaction.
2. DI for DriverManager (currently it is implemented with Singleton).
3. Add support of API tests.
4. Add support of Mocks.
5. It might be extended with Selenide library.
6. Support of External Running labs like Browserstack or dockerized containers.
