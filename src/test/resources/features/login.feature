Feature: Login With valid Credential

  Scenario: Successful Login with valid credentials
    Given The user navigates to application URL and logo is displayed
    When The user click on Login from Navbar
    And The user enter valid User name as "David02" and Password as "David@123" in the modal
    And The user clicks on login button
    Then The User should be successfully logged in and the username displayed webpage
