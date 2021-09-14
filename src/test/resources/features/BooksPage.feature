Feature: Verify Book Store page features

  Background:
    Given I am on "Book Store" page

    Scenario Outline: Verify search is working correctly
      When I enter "<value>" into search box
      Then only books which meet the search criteria are displayed
      Examples:
        |value     |
        |git       |
        |1234      |
        |JaVaScript|
        |KYLE      |
        |St@rch    |

    @bug
    Scenario Outline: Verify sorting is working correctly
      When I click on "<column>" column
      Then books are sorted by "<column>" in "ascending" order
      When I click on "<column>" column
      Then books are sorted by "<column>" in "descending" order
      Examples:
        |column   |
        |Title    |
        |Author   |
        |Publisher|


    Scenario: Verify book content
      When I click on random book
      Then appropriate book page is opened