# SaucelabsGreen Cucumber

This project is a mobile automation testing framework for Android applications using Appium, Selenium, Cucumber, and TestNG. It is designed to run BDD-style tests with feature files and step definitions, and integrates with Allure for reporting.

## Features
- Android automation with Appium
- BDD testing with Cucumber (Gherkin feature files)
- TestNG integration for test execution
- Allure reporting for rich test results
- Page Object Model structure for maintainable code
- Faker library for generating test data

## Project Structure
```
pom.xml
src/
  main/
    java/
      org/saucelabs/
        constants/
        context/
        driver/
        enums/
        pages/
    resources/
  test/
    java/
      hooks/
      runner/
      steps/
    resources/
      apk/
      features/
target/
  cucumber-report/
  allure-results/
  surefire-reports/
```

- **src/main/java/org/saucelabs/**: Main framework code (drivers, pages, context, constants)
- **src/test/java/**: Test hooks, runner, and step definitions
- **src/test/resources/features/**: Cucumber feature files (Gherkin)
- **src/test/resources/apk/**: Android APKs for testing
- **target/**: Generated reports and results

## Getting Started

### Prerequisites
- Java 18+
- Maven
- Android emulator/device

### Setup
1. Clone the repository:
  ```sh
  git clone <repo-url>
  cd saucelabsgreen-cucumber
  ```
2. Start the Appium server:
  ```sh
  appium
  ```
  (Make sure Appium is installed globally. If not, install with `npm install -g appium`.)
3. Install dependencies:
  ```sh
  mvn clean install
  ```
4. Place your APK in `src/test/resources/apk/`.
5. Update desired capabilities in `AndroidDriverInstance.java` if needed.

### Running Tests
- To run all tests:
  ```sh
  mvn test
  ```
- To run with Allure report generation:
  ```sh
  mvn clean test
  mvn allure:serve
  ```
- Cucumber HTML reports are generated in `target/cucumber-report/`.
- Allure results are in `target/allure-results/`.

#### Running Specific Features or Tags
To run specific scenarios or features, check the `tags` configuration in `src/test/java/runner/RunnerTest.java`:

```java
@CucumberOptions(
  ...existing config...
  tags = "@Regression", // Change this to match the tags in your feature files
  ...existing config...
)
```

- Update the `tags` value to match the tag(s) in your feature files (e.g., `@Smoke`, `@Login`, etc.).
- You can use logical operators, e.g. `tags = "@Smoke and not @WIP"`.
- Only scenarios/features with matching tags will be executed.

Refer to your feature files in `src/test/resources/features/` to choose the appropriate tags.

## Reporting
- **Allure**: Rich, interactive test reports (`mvn allure:serve`)
- **Cucumber**: HTML, XML, and JSON reports in `target/cucumber-report/`
- **Surefire**: Standard TestNG/JUnit reports in `target/surefire-reports/`

## Report Video Evidence
[Report](https://drive.google.com/drive/folders/1pkUcq3b11tRfCJ5-N3yiWkwzpfQuzxEt?usp=sharing)
