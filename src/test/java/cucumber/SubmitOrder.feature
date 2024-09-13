
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce Website
#background thực thi trước mỗi kịch bản 

  @Regression
  Scenario Outline: PPositive Test of Submitting the order 
	#Kiểm tra tích cực về việc gửi đơn đặt hàng
    Given Logged in with username <email> and password <password>
    When I add <productName> to Cart
    And Checkout <productName> and submit the order 
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

  Examples: 
      | email                 | password  | productName  |
      | vuthinhanai@gmail.com | Nhanai44@ | ZARA COAT 3  |

