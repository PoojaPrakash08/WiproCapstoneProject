@login
Feature: User Login (SkyJoy)

  Scenario Outline: Login with invalid credentials
    Given I open the VietJet Air website for Login
    Given I navigate to the Login page
    When I enter phone number "<phone>"
    And I click sign in
    Then I should see a login error message "Account does not exist yet. Please check again."

    Examples:
      | phone      |
      | 987654321  |
