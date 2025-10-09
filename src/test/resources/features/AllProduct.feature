Feature: All Product on Home Page

  Scenario: View product on Homepage
    Given The user navigates to application URL and logo is displayed
    Then Count the number of products displayed on homepage
    And Display all the product names on the homepage

  Scenario: Click on product and verify the product detail page
    Given The user navigates to application URL and logo is displayed
    When The user click on the product "Sony xperia z5"
    Then Product detail page should be displayed "Sony xperia z5"
