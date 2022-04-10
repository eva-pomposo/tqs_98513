Feature: Travel purchase

  Scenario: Buy a trip from Paris to Buenos Aires
    When I navigate to "https://blazedemo.com/"
    Then A new page with the tilte "BlazeDemo" appears
    When I choose departure city "Paris"
        And I choose destination city "Buenos Aires"
        And I click Find Flights
    Then A new page with the tilte "BlazeDemo - reserve" appears
    When I click Choose This Flight
    Then A new page with the tilte "BlazeDemo Purchase" appears
    When I write the name "Eva"
        And I write the address "123 Main"
        And I write the city "Aveiro"
        And I write the state "Portugal"
        And I write the zipcode "1234-567"
        And I write the credit card number "12345678"
        And I write the credit card month "12"
        And I write the credit card year "2018"
        And I write the name on card "Eva"
        And I click Purchase Flight
    Then A new page with the tilte "BlazeDemo Confirmation" appears