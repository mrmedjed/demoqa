Feature: Text box validations
  
  Background:
    Given I am on "Text Box" page

  Scenario: Verify first name field
    Given I enter "Jelena je najbolja!!!!" into "name" field
    When I click on submit button
    Then following submitted fields are displayed: "name"

  Scenario: Verify email field
    Given I enter "lune@lune.com" into "email" field
    When I click on submit button
    Then following submitted fields are displayed: "email"

  Scenario: Verify current address field
    Given I enter "Vuka Karadzica 9" into "current address" field
    When I click on submit button
    Then following submitted fields are displayed: "current address"

  Scenario: Verify permanent address field
    Given I enter "Kraljevacka 68" into "permanent address" field
    When I click on submit button
    Then following submitted fields are displayed: "permanent address"

  Scenario: Verify all fields
    Given I enter "Jelena je najbolja!!!!" into "name" field
    And I enter "lune@lune.com" into "email" field
    And I enter "Vuka Karadzica 9" into "current address" field
    And I enter "Kraljevacka 68" into "permanent address" field
    When I click on submit button
    Then following submitted fields are displayed: "all"


