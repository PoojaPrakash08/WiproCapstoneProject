@bookingFlow
Feature: Complete Booking Flow

  Scenario Outline: Successful end-to-end booking
    Given I open the VietJet Air website for FlightSearch
    When I select origin "<origin>" and destination "<destination>"
    And I select departure day "<departDay>" and return day "<returnDay>"
    And I click search flights
    Then I should see flight options displayed
    And I select a departure flight and continue
    And I select a return flight and continue
    Then I should see Passenger Details page
    When I enter Last Name "<lastName>", First Name "<firstName>", DOB "<dob>", Phone "<phone>", Email "<email>", National ID "<nid>"
    And I click continue on passenger page
    And I skip add-ons and proceed to payment page
    Then I should see Payment Page

    Examples:
      | origin | destination | departDay | returnDay | lastName   | firstName | dob        | phone      | email              | nid      | cardNum            | cardName  | expiry | cvv | address       | city   | country |
      | SGN    | BMV         | 21        | 25        | Belgaumkar | Pooja     | 15/06/2002 | 9876543210 | pooja@testmail.com | A1234567 | 1234567891234   | Pooja B   | 12/28  | 123 | 123 Main St   | Hanoi  | Vietnam |

