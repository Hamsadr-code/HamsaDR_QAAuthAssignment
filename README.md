# HamsaDR QA Authentication Automation

## Project Overview

This project is a Selenium WebDriver and TestNG based automation framework
developed for testing authentication and registration functionality of the
Demo Web Shop application.

The framework follows the Page Object Model (POM) structure to separate
test logic, page locators, reusable browser actions, configuration and
utility functions.

---

## Application Under Test

Application: Demo Web Shop

Registration:
https://demowebshop.tricentis.com/register

Login:
https://demowebshop.tricentis.com/login

---

## Technologies Used

- Java
- Selenium WebDriver
- TestNG
- External JAR dependencies
- Eclipse IDE
- Chrome Browser
- Git/GitHub
- Page Object Model

---

## Project Structure

src
├── base
│   └── BasePage.java
│
├── pages
│   ├── DashboardPage.java
│   ├── LoginPage.java
│   └── RegistrationPage.java
│
├── tests
│   ├── RegistrationTests.java
│   ├── LoginTests.java
│   └── DashboardSecurityTests.java
│
└── utils
    ├── ConfigReader.java
    └── ScreenshotUtil.java