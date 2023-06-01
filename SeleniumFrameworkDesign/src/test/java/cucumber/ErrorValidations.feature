@tag
Feature: ErrorValidations
  I want to use this template for my feature file

    @tag1
  Scenario: Title of your scenario
    Given I landed on Ecommerce page
     When logged in with username <name> and password <password>
     Then "Incorrect email or password." message is displayed
  
    Examples: 
      | name                  | password  | 
      | skbhukta@selenium.com | Learn@199 | 
  
