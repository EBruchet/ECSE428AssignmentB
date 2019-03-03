Feature: Send an email with an attached images


  Background:
    Given I am on a Gmail page
    And I am logged in


  Scenario Outline: I try to send an email to a valid email address with an attached image
    When I click on the Compose button
    And I specify a valid email address as "<email>"
    And I click on the Attach File button
    And I select the desired image as "<path>"
    And I click the Send button
#    Then I
    Examples:
      | email   | path |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Spicy.PNG |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Ran.jpg |

#  Scenario: I try to send an email to a valid email address with an attached image greater than 25 MB
#    When I click on the Compose button
#    And I specify a valid email address
#    And I click on the Attach File button
#    And I select the desired large image
#    Then a notification appears saying the file will be sent as Google Drive link
#    And I click the Send button
##    Then I
##    Examples:
##      |  |
#
#  Scenario: I try to send an email to a valid email address with an attached image using another button
#    When I click on the Compose button
#    And I specify a valid email address
#    And I click on the Insert Photo button
#    And I click the As Attachment button
#    And I select the desired image
#    And I click the Send button
##    Then I
##    Examples:
##      |  |
#
#  Scenario: I try to send an email to an invalid email address with an attached image
#    When I click on the Compose button
#    And I specify an invalid email address
#    And I click on the Attach File button
#    And I select the desired image
#    And I click the Send button
#    Then I should see an error message
##    Examples:
##      |  |
