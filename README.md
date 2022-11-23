# RoadSmart - An Example Java Application for Testing

## Introduction

RoadSmart is a command-line application used to provide information for major roads all over the world. While it could be helpful, it is also extremely buggy for a CLI app.

This is an application that can be used to show examples of unit, component and fuzz tests that find functional and security bugs in Java code.

This project is maintained by [Code Intelligence](https://code-intelligence).

## Prerequisites

This project requires:

- Java JDK 8+
- Maven
- [cifuzz CLI](https://github.com/CodeIntelligenceTesting/cifuzz#installation)

and has been tested with IntelliJ (recommended) and VS Code.

This project uses [JUnit 5](https://junit.org).

## Installation

This is a standard Maven Java project. You can get dependencies by running

```bash
mvn install
```

## Running the Application

RoadSmart is a CLI application. You can currently run this in your IDE by running the `main` method in the Main class.

## Tests

This is the interesting bit. Tests are located in the standard location of `src/test/java`. Currently, there are

- Unit tests: these test functionality of various classes, and may be used for [test-driven development](https://en.wikipedia.org/wiki/Test-driven_development). These can be executed from your IDE directly or running `mvn test`.
- Component Tests: _Coming soon!_
- Fuzz Tests: These tests find possible security issues such as [SQL Injections](https://www.code-intelligence.com/blog/sql-injections). Fuzz tests may be executed using `cifuzz`, a CLI fuzz tool. More information can be found [here](https://github.com/CodeIntelligenceTesting/cifuzz).

### Unit Tests

To run all unit tests, you can call
```bash
mvn test
```
from the command line or you can run tests individually in your IDE by clicking "Run" next to them.

### Fuzz Tests

To run a single fuzz test, run the following from the root of the project:

```bash
cifuzz run <name_of_fuzz_test_class>
```

For example, you can run
```bash
cifuzz run InformationDatabaseFuzzTest
```

to execute the fuzz test `InformationDatabaseFuzzTest`.

## Pull Requests

Pull requests welcome!

Please read [CONTRIBUTING.md](./CONTRIBUTING.md) before submitting your pull requests.