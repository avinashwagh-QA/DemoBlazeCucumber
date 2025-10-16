# 🧩 DemoBlaze Automation Framework

### 🚀 Automated UI Testing Framework using Java, Selenium, and Cucumber (BDD)

This project is a **complete automation testing framework** built around the [DemoBlaze](https://www.demoblaze.com/) web application.
It’s designed to **demonstrate real-world automation design**, covering user journeys like **Sign Up, Login, Product Details, Add to Cart, and Checkout**.

The goal is to showcase a **scalable, maintainable, and professional automation structure** — built entirely from scratch for learning and portfolio purposes.

---

## 📘 Table of Contents

* [🧠 Project Overview](#-project-overview)
* [🧱 Framework Architecture](#-framework-architecture)
* [⚙️ Tech Stack Used](#️-tech-stack-used)
* [🧩 Framework Features](#-framework-features)
* [📁 Folder Structure](#-folder-structure)
* [🧪 Test Scenarios Covered](#-test-scenarios-covered)
* [🏃‍♂️ How to Run Tests](#️-how-to-run-tests)
* [📊 Reporting](#-reporting)
* [🧰 Utilities and Helpers](#-utilities-and-helpers)
* [🔮 Future Enhancements](#-future-enhancements)
* [👤 Author](#-author)

---

## 🧠 Project Overview

This automation framework validates key functionalities of the DemoBlaze e-commerce site using **Behavior-Driven Development (BDD)** with **Cucumber and Selenium WebDriver**.

The suite includes:

* User Authentication: Signup, Login, Logout
* Product Browsing: Product details and homepage product list
* Cart Management: Add/remove products, price validation
* Checkout Flow: Complete order placement, alert verification, and confirmation

---

## 🧱 Framework Architecture

The framework follows a **Page Object Model (POM)** design pattern integrated with **Cucumber BDD**, ensuring separation of test logic, UI elements, and data.

```
┌────────────────────────────┐
│        Feature Files       │  -->  Gherkin test cases
└────────────┬───────────────┘
             │
┌────────────▼───────────────┐
│       Step Definitions     │  -->  Test steps linked to UI methods
└────────────┬───────────────┘
             │
┌────────────▼───────────────┐
│         Page Objects       │  -->  Encapsulate locators & UI logic
└────────────┬───────────────┘
             │
┌────────────▼───────────────┐
│       Utilities / Base     │  -->  WaitHelper, ConfigReader, Hooks, etc.
└────────────┬───────────────┘
             │
┌────────────▼───────────────┐
│        Reports & Logs      │  -->  Extent HTML + Log4j + Screenshots
└────────────────────────────┘
```

---

## ⚙️ Tech Stack Used

| Category             | Tools / Frameworks                      |
| -------------------- | --------------------------------------- |
| Programming Language | Java                                    |
| Build Tool           | Maven                                   |
| Test Framework       | Cucumber (BDD)                          |
| Automation Library   | Selenium WebDriver                      |
| Logging              | Log4j                                   |
| Reporting            | Extent Reports                          |
| Wait Mechanism       | Custom WaitHelper (Explicit Waits)      |
| Configuration        | Properties File                         |
| Screenshot Capture   | On Test Failure                         |
| Test Data            | Scenario Outline / Hardcoded (for demo) |

---

## 🧩 Framework Features

✅ Page Object Model (POM)
✅ Cucumber BDD Gherkin Syntax
✅ Centralized WebDriver Management via BaseClass
✅ Custom WaitHelper for element synchronization
✅ Log4j integrated logging
✅ Extent Report with screenshot on failure
✅ Configurable browser and environment setup
✅ Screenshot utility with timestamp
✅ Hooks for setup, teardown, and cleanup
✅ Tags for Smoke, Sanity, and Regression
✅ Modular and maintainable folder structure

---

## 📁 Folder Structure

```
DemoBlazeCucumber/
│
├── src
│   ├── main
│   │   └── java
│   │       ├── factory/             # BaseClass, Driver setup, Config
│   │       ├── utilities/           # WaitHelper, Screenshot, ConfigReader, etc.
│   │       └── pageObjects/         # Page Object classes
│   │
│   └── test
│       ├── java
│       │   └── stepDefinitions/     # Step definitions mapped to feature steps
│       │   └── hooks/               # Hooks for setup/teardown
│       └── resources
│           ├── features/            # Feature files in Gherkin format
│           ├── config.properties    # Environment config
│           └── log4j.properties     # Logging configuration
│
├── reports/                         # Extent HTML Reports
├── screenshots/                     # Captured screenshots
├── pom.xml                          # Maven dependencies
└── README.md                        # Project documentation
```

---

## 🧪 Test Scenarios Covered

### 🔐 **Login & Logout**

* Successful login with valid credentials
* Invalid credentials alert verification
* Empty input alert validation
* Logout after login verification

### 🧍‍♂️ **Signup**

* Successful signup
* Signup with existing user
* Empty field validation
* Scenario Outline with multiple users

### 🛒 **Product Listing & Details**

* Verify product count on homepage
* Validate product detail page navigation

### 🧰 **Add to Cart**

* Add product without login
* Add product after login
* Verify cart product names and prices
* Verify total price
* Remove product from cart
* Multiple product addition

### 💳 **Checkout Flow**

* Successful checkout with valid details
* Alert validation when fields are empty
* Close button functionality verification

---

## 🏃‍♂️ How to Run Tests

### 🧩 Run from Maven Command Line

```bash
mvn test
```

### ▶️ Run with Specific Tag

```bash
mvn test -Dcucumber.filter.tags="@Smoke"
```

### 🌐 Run with Specific Browser

```bash
mvn test -Dbrowser=chrome
```

---

## 📊 Reporting

* Reports are automatically generated after each run.
* Default location:

  ```
  /reports/ExtentReport.html
  ```
* Each failed step automatically includes a screenshot.
* Logs are generated via Log4j under `/logs/`.

📸 **Sample Report:**
![Extent Report Screenshot](https://via.placeholder.com/600x250?text=Extent+Report+Preview)

---

## 🧰 Utilities and Helpers

| Utility               | Purpose                                                |
| --------------------- | ------------------------------------------------------ |
| **WaitHelper**        | Explicit waits for visibility, clickability, etc.      |
| **ConfigReader**      | Loads browser, URL, timeout from properties file       |
| **ScreenshotUtility** | Captures screenshots on test failure                   |
| **AlertHelper**       | Handles browser alerts gracefully                      |
| **JavaScriptHelper**  | Scrolls and performs JS-based clicks                   |
| **Hooks**             | Initializes and quits WebDriver before/after scenarios |

---



