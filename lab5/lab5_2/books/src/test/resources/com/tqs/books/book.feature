Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Background: A Library
    Given I have the following books in the store
      | title                                | author          | published    |
      | One good book                        | Anonymous       | 14/10/2013   |   
      | Some other book                      | Tim Tomson      | 23/10/2014   |
      | How to cook a dino                   | Fred Flintstone | 10/10/2010   |
 
  Scenario: Search books by publication year
    When the customer searches for books published between 01/01/2012 and 31/12/2015
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'

  Scenario: Search books by author
    When the customer searches for books by author 'Tim Tomson'
    Then 1 books should have been found
      And Book 1 should have the title 'Some other book'

  Scenario: Search books by title
    When the customer searches for books by title 'How to cook a dino'
    Then 1 books should have been found
      And Book 1 should have the title 'How to cook a dino'