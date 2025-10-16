Feature: Sign up Functionality

  Background:
    Given The user navigates to application URL and logo is displayed
    And The user clicks on sign up


  Scenario: verify signup using correct credential
     When The user enter valid User name as "SmithParker02" and Password as "Smith@123" in the sing up modal
     And The user clicks on sign up button
     Then An alert should be displayed with message "Sign up successful."

  Scenario: Verify Sing up without any credential
     When The user clicks on sign up button
     Then An alert should be displayed an error message "Please fill out Username and Password."

  Scenario: Verify sign up with already register user
     When The user enter valid User name as "JhonSnow02" and Password as "Jhon@123" in the sing up modal
     And The user clicks on sign up button
     Then An alert should be displayed an error message "This user already exist."

  Scenario Outline: verify signup using correct credential
    When The user enter valid User name as "<Username>" and Password as "<Password>" in the sing up modal
    And The user clicks on sign up button
    Then An alert should be displayed with message "Sign up successful."
    Examples:
      | Username | Password  |
      | Alexa02  | Alexa@123 |
      | Siri02   | Siri@123  |
      | Mike02   | Mike@123  |







