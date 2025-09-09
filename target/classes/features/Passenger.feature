@passenger
Feature: Passenger Details

  Scenario: Passenger details with missing name
    Given I am on the Passenger Details page
    When I leave Last Name and First Name blank DOB "<dob>", Phone "<phone>", Email "<email>", National ID "<nid>"
    And I click continue button on passenger page
    Then I should see a passenger page error message "Please fill in all required information"
