
@tag
Feature: Error Validatons 



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Website
    When Logged in with invai username <email> and password <password>
    Then "Incorrect email or password." message is displayed 


   Examples: 
      | email                 | password  | 
      | vuthinhanai@gmail.com | wrongpassword |
