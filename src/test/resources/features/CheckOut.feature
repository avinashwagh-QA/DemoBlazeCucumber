Feature: Checkout flow Verification

  Background:
    Given The user navigates to application URL and logo is displayed

    @Sanity
    Scenario: Verify use can check out Successfully from cart page
      Given The user adds the following products to cart
        | Product Name      | Price |
        | Sony xperia z5    | 320   |
        | Samsung galaxy s7 | 800   |
      When User navigates to Cart Page then Cart should be displayed all the product
        | Product Name      |
        | Sony xperia z5    |
        | Samsung galaxy s7 |
      And The user click on the Place order
      And User the user fill outs the checkout details
        | Name        | John Doe            |
        | Country     | USA                 |
        | City        | New York            |
        | Credit Card | 1234 5678 9012 3456 |
        | Month       | 12                  |
        | Year        | 2025                |
      And User confirms the purchase
      Then A Confirmation message "Thank you for your purchase!" should be displayed
      And The Order details should include
        | Amount      | 1120 USD            |
        | Card Number | 1234 5678 9012 3456 |
        | Name        | John Doe            |
        | Date        | 14/9/2025           |
      And Clicking on ok button then user should be navigated to Home Page and title should be "PRODUCT STORE"

    @Regression
    Scenario: Verify when user does not enter any value then alert appears of enter details
      When The user click on the product "Sony xperia z5"
      And Product detail page should be displayed "Sony xperia z5"
      And Click on the add to cart button
      And Success message should be displayed on the page "Product added"
      And The product "Sony xperia z5" should be successfully added to the cart
      And The user click on the Place order
      And User confirms the purchase
      Then Alert should be displayed with message "Please fill out Name and Creditcard."

    @Regression
    Scenario: Verify the on the order the close button works
      When The user click on the product "Sony xperia z5"
      And Product detail page should be displayed "Sony xperia z5"
      And Click on the add to cart button
      And Success message should be displayed on the page "Product added"
      And The product "Sony xperia z5" should be successfully added to the cart
      And The user click on the Place order
      Then User clicks on close button then modal should be closed



