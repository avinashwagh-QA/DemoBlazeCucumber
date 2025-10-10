Feature: Add to Cart functionality

  Background:
    Given The user navigates to application URL and logo is displayed



  Scenario: Verify Product added to cart Successfully without login
    When The user click on the product "Sony xperia z5"
    And Product detail page should be displayed "Sony xperia z5"
    And Click on the add to cart button
    And Success message should be displayed on the page "Product added"
    Then The product "Sony xperia z5" should be successfully added to the cart


  Scenario: Verify product can be added after successfully login
    Given The user click on Login from Navbar
    When The user enter valid User name as "David02" and Password as "David@123" in the modal
    And The user clicks on login button
    And The User should be successfully logged in and the username displayed webpage
    And The user click on the product "Samsung galaxy s6"
    And Click on the add to cart button
    And Success message should be displayed on the page "Product added."
    Then The product "Samsung galaxy s6" should be successfully added to the cart


    Scenario: Verify product name and price are displayed correctly on cart page for single product
      When The user click on the product "Sony xperia z5"
      And Product detail page should be displayed "Sony xperia z5"
      And Click on the add to cart button
      And Success message should be displayed on the page "Product added"
      Then The product "Sony xperia z5" should be successfully added to the cart
      And The price of the product "Sony xperia z5" in the cart should be "320"


    Scenario: Verify Total price for single product is same as product price
      When The user click on the product "Sony xperia z5"
      And Product detail page should be displayed "Sony xperia z5"
      And Click on the add to cart button
      And Success message should be displayed on the page "Product added"
      And The price of the product "Sony xperia z5" in the cart should be "320"
      And The total price in the cart should be equal to the product price "320"

    Scenario: Verify user can remove single product from the cart
      When The user click on the product "Sony xperia z5"
      And Product detail page should be displayed "Sony xperia z5"
      And Click on the add to cart button
      And Success message should be displayed on the page "Product added"
      Then The user deletes the product "Sony xperia z5" from the cart and it should be removed




