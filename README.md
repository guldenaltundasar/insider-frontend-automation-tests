
# Insider Frontend Automation Task

## Overview

This project is a test automation suite developed for **Insider's website**. It utilizes **Selenium WebDriver** for UI testing, incorporating various functionalities such as navigating through pages, filtering job listings, verifying content presence, and ensuring that user interactions lead to expected outcomes. The automation scripts are written in **Java** and orchestrated through **TestNG** to ensure robust testing across multiple browsers.

## Features

- **Cross Browser Testing**: Supports testing on both **Chrome** and **Firefox** with parameterized browser selection.
- **Page Object Model (POM)**: Utilizes POM for better maintainability and readability, adhering strictly to POM requirements.
- **Dynamic Element Interaction**: Interacts with web elements dynamically, including clicks, hovers, and text verifications.
- **Error Capture**: Automatically captures screenshots when tests fail, providing visual evidence for debugging.

## Setup

### Prerequisites

- **Java 18**
- **Maven**
- **Selenium WebDriver**
- **TestNG**
- **WebDriverManager**

### Dependencies

The project uses **Maven** for dependency management. Key dependencies include:

- **Selenium Java**: For browser and elements interactions.
- **WebDriverManager**: To manage browser driver compatibility.
- **TestNG**: Used for organizing tests and providing assertions.
- **Commons-IO**: For handling input/output operations, especially during file uploads.

### Installing and Running Tests

1. **Clone the repository** to your local machine.
2. **Navigate to the project directory**.
3. To run the tests using the `testing.xml` suite, execute the following Maven command:
   ```
   mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testing.xml
   ```

## Getting Started

For new team members or first-time users:
- Ensure you have all the prerequisites installed.
- Review the **Dependencies** section to verify that your local environment is correctly set up.
- Check out the `testing.xml` file to understand the structure of test suites and modify it if needed.

## Tests

### Test Scenarios

- **Home Page Verification**: Ensures the Insider home page loads correctly at `https://useinsider.com/`.
- **Careers Navigation**: Tests navigation through the “Company” menu to the “Careers” page, verifying the visibility of the Locations, Teams, and Life at Insider blocks.
- **QA Jobs Filtering**: Visits `https://useinsider.com/careers/quality-assurance/`, filters QA jobs by location ("Istanbul, Turkey") and department ("Quality Assurance"), and verifies job listings are displayed.
- **Job Details Verification**: Confirms that listed jobs contain correct details in terms of position, department, and location specifications.
- **Role Redirection**: Tests the functionality of the “View Role” button, checking redirection to the Lever Application form page.

### Structure

- **`HomePage.java`**: Handles operations on the home page.
- **`CompanyPage.java`**: Navigates to different company-related pages like Careers.
- **`CareersPage.java`**: Verifies elements on the Careers page.
- **`QAJobsPage.java`**: Manages interactions on the QA Jobs listing page.
- **`InsiderSiteTests.java`**: Contains all test methods that execute the defined test cases, leveraging the page objects for browser interactions. This class also manages test setup and teardown, providing hooks for before and after tests are run.

## Reporting

Screenshots are captured for failed tests, stored under the **`screenshots/`** directory, ensuring quick visual feedback on test failures.
