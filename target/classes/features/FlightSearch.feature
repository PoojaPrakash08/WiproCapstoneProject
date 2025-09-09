@flightsearch
Feature: Flight Search

  Scenario: Search flights with no value for origin
    Given I open the VietJet Air website for FlightSearch process
    When I select origin "" and destination "" in flightsearch
    And I select departure day "21" and return day "25" for flight
    And I click on lets go button
    Then I should see a flight search error message "Please fill in searching flight information"
