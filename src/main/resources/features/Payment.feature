@payment
Feature: Payment Module

  @Negative
  Scenario Outline: Invalid Card Number
    Given user is on Payment Method page
    When user enters invalid card details "<CardNumber>", "<CardHolder>", "<Expiry>", "<CVV>", "<Address>", "<City>", "<Country>"
    Then error message "Card number is invalid" should be displayed on Payment Page after invalid details

    Examples:
      | CardNumber       | CardHolder | Expiry | CVV | Address            | City      | Country |
      | 1234 5678 9123 4 | POOJA      | 02/30  | 123 | WhiteField, Bengaluru | Bengaluru | IN - India |