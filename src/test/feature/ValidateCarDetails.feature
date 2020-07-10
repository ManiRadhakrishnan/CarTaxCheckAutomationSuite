#Sample Feature Definition Template
Feature: Validate vehicle details

  Scenario: Validate Vehicle Details
    Given I extracted vehicle registration numbers from input text file
    When  I search a registration number on https://cartaxcheck.co.uk/
    Then  details on the website should match output text file
