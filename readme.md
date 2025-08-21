# Trello API Tests (Java + RestAssured + JUnit5 + Allure)

## 📌 Overview
This project contains a suite of **automated REST API tests** for Trello.  
The tests are written in **Java**, use **RestAssured** for API communication, and **JUnit 5** as the test framework.  
**Allure** is integrated for reporting.


## 🛠️ Requirements
- **Java 21+**
- **Maven 3.9+**
- A Trello account with generated **API Key** and **Token**  
  👉 [Get your Trello API key & token](https://trello.com/app-key)

---

## ⚙️ Configuration
Project configuration is managed in the `BaseTest` class (or optionally in a `config.properties` file).

You need to provide:
- `trelloKey` – your Trello API key
- `trelloToken` – your Trello API token
- `boardId` – the ID of an existing Trello board (or create dynamically in tests)

Allure is configured via `src/test/resources/allure.properties`:
```properties
allure.results.directory=target/allure-results
▶️ Running Tests
Run tests with Maven:


mvn clean test
Generate and open the Allure report:

mvn allure:serve -Dallure.results.directory=target/allure-results
The report will open in your default browser.

📊 Reporting
After running tests, Allure generates raw results under target/allure-results.
To view them, run:


mvn allure:serve
You will see a detailed report with test steps, requests, responses, and results.

