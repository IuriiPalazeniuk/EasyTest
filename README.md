# EasyTest Automation Framework

**BDD Automation Framework**  - for quick and easy creation of automated test scripts. 

Table of contents
=================
   * [Key features](#key-features)
   * [Technologies and frameworks](#technologies-and-frameworks)
   * [Test scenario example](#test-scenario-example)
   * [Test step example](#test-step-example)   
   * [Test runner](#test-runner)
   * [Run options](#run-options)
   * [Report](#report)

## Key features
  * BDD (Cucumber framework) is used for readability and extensibility of tests

    
## Technologies and frameworks
 * Java programming language ([Java](https://www.java.com)) 
 * Cucumber BDD framework ([Cucumber](https://cucumber.io/docs))
 * Maven build tool ([Maven](https://maven.apache.org))
 * Selenide a wrapper for Selenium WebDriver ([Selenide](https://selenide.org/documentation.html))
 

## Test scenario example

```java
  Scenario: positive case
    Given User navigates to 'http://automationpractice.com/index.php'
    When User clicks 'Search button' "//button[@name='search']"
    When User clicks browser back page
    Then Check element "//button[@type='submit']" is 'displayed'
```

## Test step example

```java
     @When("User clicks {string} {string}")
     public void userClicksOnElementByLocator(String elem, String locator) {
         log.info("User clicks on " + elem);
         clickOnElement(locator);
     }

```

## Test runner

```java
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "stepDefs",
        strict = true)
public class CucumberTestRunner {

}
```

## Run options
 * Locally via IDE (To run separate scenario you need Cucumber Java plugin to be installed)
  * Maven (possible to run tests separately by @tags):
 ```bash
    'mvn clean test mvn clean test -Dcucumber.options="--tags @API"' or 'mvn clean test mvn clean test -Dcucumber.options="--tags @UI"'
    'mvn clean test' - will run all tests
 ```
 * Possible to select browser (chrome(by default), firefox, ie, phantomjs, htmlunit, safari, opera...)
 ```bash
    -Dselenide.browser=phantomjs
```
 * Create feature file in folder 'src/test/resources/features'
 * Framework supports soft assertions, put @softAssertion tag before scenario in feature file
## Report
 * Download the latest version as zip archive from Maven Central:
   https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline
 * Unpack the archive to allure-commandline directory.
 * Navigate to bin directory.
 * Use allure.bat for Windows or allure for other Unix platforms.
 * Add allure to system PATH.
 * Execute 'allure --version' in console to make sure that allure is now available
 * Command to generate allure report: 
 ```bash
 allure serve 'path to the project'\target\allure-results
```