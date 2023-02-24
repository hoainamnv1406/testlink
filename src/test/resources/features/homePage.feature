Feature: Check home page functionality

  Background:
    Given Users want to open homepage

  @Regression
  Scenario: Verify that user is able to navigate to the homepage
    When They should be able to see the homepage
    Then They want to select a centre

  @Sanity @Regression
  Scenario: This is a fail test case example
    When They should be able to see the homepage 1
    Then They want to select a centre

  @Smoke
  Scenario: This is a error test case example
    Then They want to select one centre
