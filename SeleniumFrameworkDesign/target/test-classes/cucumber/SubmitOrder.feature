@tag
Feature: Purchase the order from Ecommerce website
I want to use this template for my feature file

  Background: 
    Given I landed on Ecommerce page
  
  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given logged in with username <name> and password <password>
     When Add the product <productName> to cart
      And Checkout <productName> and submit the order
     Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page
  
    Examples: 
      | name                  | password   | productName | 
      | skbhukta@selenium.com | Learn@1991 | ZARA COAT 3 | 
  
