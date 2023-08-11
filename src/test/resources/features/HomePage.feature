@Challenge @UserStory1
Feature: Home Page Section

  Scenario: AC-1:Login and verify the dashboard page:

    Given I launch the application and Login as preferred user
    Then I verify the dashboard
    And I logout and close the browser

