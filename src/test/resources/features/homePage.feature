Feature: Check home page functionality

  Background:
    Given Users want to open homepage

  @Test
  Scenario: Verify that user is able to navigate to the homepage
    When They should be able to see the homepage
    Then They want to select a centre

  @Test
  Scenario: Verify that user is able to navigate to the homepage1
    When They should be able to see the homepage 1
    Then They want to select a centre
