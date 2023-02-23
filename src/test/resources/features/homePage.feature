Feature: Check home page functionality

  Background:
    Given Users want to open homepage

  @Regression
  Scenario: Verify that user is able to navigate to the homepage
    When They should be able to see the homepage
    Then They want to select a centre
#
#  @Sanity
#  Scenario: Verify that user is able to navigate to the homepage1
#    When They should be able to see the homepage 1
#    Then They want to select a centre
#
#  @Smoke @Regression
#  Scenario: Verify that user is able to navigate to the homepage2
#    Then They want to select a centree
