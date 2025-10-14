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




