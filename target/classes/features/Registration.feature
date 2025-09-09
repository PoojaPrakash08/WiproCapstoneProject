@registration
Feature: User Registration (SkyJoy Account)

  Scenario Outline: Successful registration with valid details
    Given I open the VietJet Air website for Registration
    And I navigate to the Registration page
    When I enter Family Name "<familyName>", Given Name "<givenName>", Phone "<phone>", Birthday "<dob>", Email "<email>"
    And I click confirm
    Then I should see a success message "Registration successful."

    Examples:
      | familyName | givenName | phone       | dob        | email                |
      | Belgaumkar | Pooja     | 987654321   | 15/06/2002 | pooja@testmail.com   |

  
    Scenario: Registration with invalid email
    Given I open the VietJet Air website for Registration
    And I navigate to the Registration page
    When I enter Family Name "<familyName>", Given Name "<givenName>", Phone "<phone>", Birthday "<dob>", Email "<email>"
    Then I should see a registration email error message "Email is not valid"
    
    Examples:
      | familyName | givenName | phone       | dob        | email     |
      | Belgaumkar | Pooja     | 987654321   | 15/06/2002 | abcdefg   |
