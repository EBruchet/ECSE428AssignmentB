Feature: Send an email with an attached images


  Background:
    Given I am on a Gmail page
    And I am logged in


  Scenario Outline: I try to send an email to a valid email address with an attached image
    When I click on the Compose button
    And I specify a valid email address as "<email>"
    And I click on the Attach File button
    And I select the desired image as "<path>"
    Then completed the upload of file named "<arialabel>"
    And I click the Send button
    And I click the Sent folder button
    Then my email should be displayed in the sent folder
    Examples:
      | email   | path | arialabel |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Spicy.PNG | Uploading attachment: Spicy.PNG. Press delete to cancel |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Ran.jpg |   Uploading attachment: Ran.jpg. Press delete to cancel |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\KidA.png |  Uploading attachment: KidA.png. Press delete to cancel |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\MBDTF.jpg | Uploading attachment: MBDTF.jpg Press delete to cancel |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\KSG.png |   Uploading attachment: KSG.png Press delete to cancel |


#  Scenario Outline: I try to send an email to a valid email address with an attached image greater than 25 MB
#    When I click on the Compose button
#    And I specify a valid email address as "<email>"
#    And I click on the Attach File button
#    And I select the desired large image as "<path>"
#    Then a notification appears saying the large file is being sent as a Google Drive Link
#    Then the Google Drive Link appears in email with name "<filename>"
#    And I click the Send button
#    And I click the Sent folder button
#    Then my email should be displayed in the sent folder
#    Examples:
#      | email  | path | filename |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\OverLargeImage.jpg | OverLargeImage.jpg |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\OverLargeImage.jpg | OverLargeImage.jpg |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\OverLargeImage.jpg | OverLargeImage.jpg |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\OverLargeImage.jpg | OverLargeImage.jpg |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\OverLargeImage.jpg | OverLargeImage.jpg |
#
#  Scenario Outline: I try to send an email to a valid email address with an attached image using another button
#    When I click on the Compose button
#    And I specify a valid email address as "<email>"
#    And I click on the Insert Photo button
#    And I click the As Attachment button
#    And I click the Select Files From Your Device button and a file path as "<path>"
#    And I click the Send button
#    And I click the Sent folder button
#    Then my email should be displayed in the sent folder
#    Examples:
#      | email   | path |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Spicy.PNG |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Ran.jpg |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\KidA.png |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\MBDTF.jpg |
#      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\KSG.png |


#  Scenario Outline: I try to send an email to an invalid email address with an attached image
#    When I click on the Compose button
#    And I specify an invalid email address as "<email>"
#    And I click on the Attach File button
#    And I select the desired image as "<path>"
#    Then completed the upload of file named "<filename>"
#    And I click the Send button
#    Then I should see an error message
#    Examples:
#      | email   | path | filename |
#      | asdf | C:\Users\Evan\Downloads\Spicy.PNG | Uploading attachment: Spicy.PNG Press delete to cancel |
#      | fsda | C:\Users\Evan\Downloads\Ran.jpg |   Uploading attachment: Ran.jpg Press delete to cancel |
#      | yeet | C:\Users\Evan\Downloads\KidA.png |  Uploading attachment: KidA.png Press delete to cancel |
#      | yata | C:\Users\Evan\Downloads\MBDTF.jpg | Uploading attachment: MBDTF.jpg Press delete to cancel |
#      | spaget | C:\Users\Evan\Downloads\KSG.png | Uploading attachment: KSG.png Press delete to cancel |
