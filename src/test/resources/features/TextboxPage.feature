Feature: Verify text box page features
  
  Background:
    Given I am on "Text Box" page

  Scenario: Verify first name field
    Given I enter "John" into "name" field
    When I click on submit button
    Then following submitted fields are displayed: "name"

  Scenario: Verify email field
    Given I enter "lune@lune.com" into "email" field
    When I click on submit button
    Then following submitted fields are displayed: "email"

  Scenario: Verify current address field
    Given I enter "123 Paradise st" into "current address" field
    When I click on submit button
    Then following submitted fields are displayed: "current address"

  Scenario: Verify permanent address field
    Given I enter "123 Hell st" into "permanent address" field
    When I click on submit button
    Then following submitted fields are displayed: "permanent address"

  Scenario: Verify all fields
    Given I enter "Jane" into "name" field
    And I enter "lune@lune.com" into "email" field
    And I enter "123 Oak st" into "current address" field
    And I enter "22 Pineapple bvd" into "permanent address" field
    When I click on submit button
    Then following submitted fields are displayed: "all"


