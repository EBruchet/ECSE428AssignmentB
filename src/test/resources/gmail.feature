Feature: Send an email with an attached images


  Background:
    Given I am logged in
    And I am on a Gmail page


  Scenario Outline: I try to send an email to a valid email address with an attached image
    When I click on the Compose button
    And I specify a valid email address
    And I click on the Attach File button
    And I select the desired image
    And I click the Send button
    Then I
    Examples:
      |  |

  Scenario Outline: I try to send an email to an invalid email address with an attached image
    When I click on the Compose button
    And I specify an invalid email address
    And I click on the Attach File button
    And I select the desired image
    And I click the Send button
    Then I should see an error message
    Examples:
      |  |
