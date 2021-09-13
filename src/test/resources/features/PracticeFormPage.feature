Feature: Verify practice form
  
  Background:
    Given I am on "Practice Form" page
    @lune
    Scenario: Verify all fields can be populated and submitted
      When I pick a date
      |day|month   |year|
      |20 |February|1966|
