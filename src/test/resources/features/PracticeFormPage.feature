Feature: Verify practice form page features
  
  Background:
    Given I am on "Practice Form" page

    Scenario: Verify all fields can be populated and submitted
      When I enter "James" in "first name" field
      And I enter "Bond" in "last name" field
      And I enter "medjed@medjed.com" in "email" field
      And I select gender: "Other"
      And I enter "1234123212" in "mobile number" field
      And I pick a date
        |day|month   |year|
        |20 |February|1966|
      And I enter following subjects:
        |Maths|Physics|English|
      And I select following hobbies:
        |Sports|Reading|Music|
      And I upload a picture
      And I select state "NCR" and city "Delhi"
      And I enter "232 Brooklyn road" in "current address" field
      And I submit the form
      Then form is submitted successfully
      And correct values are displayed on the form modal

    @bug
    Scenario: Verify form can be submitted upon populating mandatory fields
      When I populate all missing mandatory fields
      |firstName|lastName|gender|mobile    |
      |Lune     |Medjed  |Other |1233212332|
      And I submit the form
      Then form is submitted successfully
      And correct values are displayed on the form modal


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

    @bug
    Scenario: Verify form can be submitted twice in the same session (mandatory fields)
      When I populate all missing mandatory fields
        |firstName|lastName|gender|mobile    |
        |Lune     |Medjed  |Other |1233212332|
      And I submit the form
      Then form is submitted successfully
      And correct values are displayed on the form modal
      When I close the submission modal
      And I populate all missing mandatory fields
        |firstName|lastName|gender|mobile    |
        |Lune     |Medjed  |Other |1233212332|
      And I submit the form
      Then form is submitted successfully
      And correct values are displayed on the form modal

    @bug
    Scenario: Verify form can be submitted twice in the same session (all fields)
      When I populate all missing mandatory fields
        |firstName|lastName|gender|mobile    |
        |Lune     |Medjed  |Other |1233212332|
      And I enter "medjed@medjed.com" in "email" field
      And I pick a date
        |day|month    |year|
        |20 |September|1993|
      And I enter following subjects:
        |Maths|English|
      And I select following hobbies:
        |Sports|Reading|
      And I upload a picture
      And I enter "232 Brooklyn road" in "current address" field
      And I select state "NCR" and city "Delhi"
      And I submit the form
      Then form is submitted successfully
      And correct values are displayed on the form modal
      When I close the submission modal
      And I populate all missing mandatory fields
        |firstName|lastName|gender|mobile    |
        |Lune     |Medjed  |Other |1233212332|
      And I enter "medjed@medjed.com" in "email" field
      And I pick a date
        |day|month    |year|
        |20 |September|1993|
      And I enter following subjects:
        |Maths|English|
      And I select following hobbies:
        |Sports|Reading|
      And I upload a picture
      And I enter "232 Brooklyn road" in "current address" field
      And I select state "NCR" and city "Delhi"
      And I submit the form
      Then form is submitted successfully
      And correct values are displayed on the form modal