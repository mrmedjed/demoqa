Feature: Verify practice form
  
  Background:
    Given I am on "Practice Form" page
    @lunee
    Scenario: Verify all fields can be populated and submitted
      When I enter "medjed" in "first name" field
      And I enter "medjed" in "last name" field
      And I enter "medjed@medjed.com" in "email" field
      And I select gender: "female"
      And I enter "1234123212" in "mobile number" field
      And I pick a date
        |day|month   |year|
        |20 |February|1966|
      And I enter following subjects:
        |Maths|Physics|English|
      And I select following hobbies:
        |Sports|Reading|Music|
      And I upload a picture
      And I select state "asd" and city "asd"
      And I enter "fasdasd da da ad" in "current address" field
      And I submit the form
      Then form is submitted successfully

    @lune
    Scenario: Verify form can be submitted upon populating mandatory fields
      When I populate all mandatory fields
      And I submit the form
      Then form is submitted successfully
    @lune
    Scenario Outline: Verify form can't be submitted without mandatory values
      When I populate all mandatory fields except "<field>"
      And I submit the form
      Then form is not submitted
        Examples:
          |field       |
          |first name  |
          |last name   |
          |gender      |
          |mobile phone|

    @lune
    Scenario: Verify form can be submitted twice in the same session
      When I populate all mandatory fields
      And I submit the form
      Then form is submitted successfully
      When I close the submission modal
      And I populate all mandatory fields
      And I submit the form
      Then form is submitted successfully