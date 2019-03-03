Feature: Send an email with an attached images


  Background:
    Given I am on a Gmail page
    And I am logged in


  Scenario Outline: I try to send an email to a valid email address with an attached image
    When I click on the Compose button
    And I specify a valid email address as "<email>" with subject "<subject>"
    And I click on the Attach File button
    And I select the desired image as "<path>"
    Then completed the upload of file named "<arialabel>"
    And I click the Send button
    Then the message sent notification appears
    And I click the Sent folder button
    Then my email should be displayed in the sent folder with subject "<subject>"
    Examples:
      | email   | path | arialabel | subject |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Spicy.PNG | Attachment: Spicy.PNG. Press enter to view the attachment and delete to remove it | Spicy_Valid |
      | dreamteamlite2@gmail.com | C:\Users\Evan\Downloads\Ran.jpg |   Attachment: Ran.jpg. Press enter to view the attachment and delete to remove it | Ran_Valid |
      | dreamteamlite3@gmail.com | C:\Users\Evan\Downloads\KidA.png |  Attachment: KidA.png. Press enter to view the attachment and delete to remove it | KidA_Valid |
      | evanbruchet@gmail.com | C:\Users\Evan\Downloads\MBDTF.jpg | Attachment: MBDTF.jpg. Press enter to view the attachment and delete to remove it | MBDTF_Valid |
      | evanbruchet2@gmail.com | C:\Users\Evan\Downloads\KSG.png |   Attachment: KSG.png. Press enter to view the attachment and delete to remove it | KSG_Valid |


  Scenario Outline: I try to send an email to a valid email address with an attached image greater than 25 MB
    When I click on the Compose button
    And I specify a valid email address as "<email>" with subject "<subject>"
    And I click on the Attach File button
    And I select the desired large image as "<path>"
    Then a notification appears saying the large file is being sent as a Google Drive Link
    Then the Google Drive Link appears in email with name "<filename>"
    And I click the Send button
    Then a notification appears checking access permissions
    Then the message sent notification appears
    And I click the Sent folder button
    Then my email should be displayed in the sent folder with subject "<subject>"
    Examples:
      | email  | path | filename | subject |
      | ebbruchet@gmail.com | C:\Users\Evan\Downloads\Earth.jpg | Earth.jpg | Earth |
      | dreamteamlite2@gmail.com | C:\Users\Evan\Downloads\Oil.jpg | Oil.jpg | Oil |
      | dreamteamlite3@gmail.com | C:\Users\Evan\Downloads\LosAngeles.jpg | LosAngeles.jpg | LosAngeles |
      | evanbruchet@gmail.com | C:\Users\Evan\Downloads\Germany.jpg | Germany.jpg | Germany |
      | evanbruchet2@gmail.com | C:\Users\Evan\Downloads\Mining.jpg | Mining.jpg | Mining |

  Scenario Outline: I try to send an email to a valid email address with an attached image using another button
    When I click on the Compose button
    And I specify a valid email address as "<email>" with subject "<subject>"
    And I click on the Insert Photo button
    And I click the As Attachment button
    And I click the Select Files From Your Device button and a file path as "<path>"
    And I click the Send button
    And I click the Sent folder button
    Then the message sent notification appears
    Then my email should be displayed in the sent folder with subject "<subject>"
    Examples:
      | email   | path | subject |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Spicy.PNG | Spicy_DiffButton |
      | dreamteamlite2@gmail.com | C:\Users\Evan\Downloads\Ran.jpg | Ran_DiffButton |
      | dreamteamlite3@gmail.com | C:\Users\Evan\Downloads\KidA.png | KidA_DiffButton |
      | evanbruchet@gmail.com | C:\Users\Evan\Downloads\MBDTF.jpg | MBDTF_DiffButton |
      | evanbruchet2@gmail.com | C:\Users\Evan\Downloads\KSG.png | KSG_DiffButton |


  Scenario Outline: I try to send an email to an invalid email address with an attached image
    When I click on the Compose button
    And I specify an invalid email address as "<email>"
    And I click on the Attach File button
    And I select the desired image as "<path>"
    Then completed the upload of file named "<filename>"
    And I click the Send button
    Then I should see an error message
    Examples:
      | email   | path | filename |
      | asdf | C:\Users\Evan\Downloads\Spicy.PNG |Attachment: Spicy.PNG. Press enter to view the attachment and delete to remove it |
      | fsda | C:\Users\Evan\Downloads\Ran.jpg | Attachment: Ran.jpg. Press enter to view the attachment and delete to remove it |
      | yeet | C:\Users\Evan\Downloads\KidA.png |  Attachment: KidA.png. Press enter to view the attachment and delete to remove it |
      | yata | C:\Users\Evan\Downloads\MBDTF.jpg | Attachment: MBDTF.jpg. Press enter to view the attachment and delete to remove it |
      | spaget | C:\Users\Evan\Downloads\KSG.png | Attachment: KSG.png. Press enter to view the attachment and delete to remove it |
