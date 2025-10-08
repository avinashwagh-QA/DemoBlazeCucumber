Feature: Login Functionality

  Background:
    Given The user navigates to application URL and logo is displayed
    And  The user click on Login from Navbar

  @Smoke
  Scenario: Successful Login with valid credentials
    When The user enter valid User name as "David02" and Password as "David@123" in the modal
    And The user clicks on login button
    Then The User should be successfully logged in and the username displayed webpage

  @Regression
  Scenario: Log in with invalid credentials
    When The user enter valid User name as "tyrq1231" and Password as "123456" in the modal
    And The user clicks on login button
    Then An Alert should be displayed with message "User does not exist."

  @Negative
  Scenario Outline: Log in with invalid credentials
    When The user enter valid User name as "<Username>" and Password as "<Password>" in the modal
    And The user clicks on login button
    Then An Alert should be displayed with message "<Error_Message>"
    Examples:
      | Username | Password  | Error_Message                          |
      | Wrong123 | David@123 | User does not exist.                   |
      | David02  | 7789955   | Wrong password.                        |
      |          |           | Please fill out Username and Password. |

    @Sanity
    Scenario: Verify Logout after login
     Given The user loged in with valid User name as "David02" and Password as "David@123" in the input
     When The user clicks on logout button
     Then Login link should be visible on the Homepage
