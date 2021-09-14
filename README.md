# demoqa test automation

This repository contains UI tests for https://demoqa.com 

## Technologies

* [Java 8] programming language
* [Selenium] browser automation library
* [Cucumber] test framework

## Project dependencies

This is maven project and therefore maven handles all the dependencies.

## Tests execution

Tests can be executed via TestRunner class. More information about the test execution can be found in the class itself. Dependencies are handled by maven, but for successful run, additional plugins might be required (like 'Cucumber for Java' and 'Gherkin' plugins).

Tests can be executed via multiple browsers. Just change the browser value, which can be found in config.properties file.
Also, tests can be executed in headless mode. The headless value can also be found in config.properties file.
