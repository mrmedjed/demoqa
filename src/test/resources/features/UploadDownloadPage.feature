Feature: Verify upload/download page features

  Background:
    Given I am on "Upload/Download" page


  Scenario: Verify upload file
    Given I upload a file type ".jpeg"
    Then appropriate message is displayed


  Scenario: Verify upload file
    Given I upload a file type ".pdf"
    Then appropriate message is displayed


  Scenario: Verify upload file
    Given I upload a file type ".xlsx"
    Then appropriate message is displayed


  Scenario: Verify upload file
    Given I upload a file type ".docx"
    Then appropriate message is displayed


  Scenario: Verify upload file
    Given I upload a file type ".txt"
    Then appropriate message is displayed


  Scenario: Verify download file
    Given I download a file
    Then verify that file is downloaded