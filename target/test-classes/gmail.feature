Feature: Send an email with an attached images

ggm
  Background:
    Given I am on a Gmail page
    And I am logged in


  Scenario Outline: I try to send an email to a valid email address with an attached image
    When I compose an email
    And I specify a valid email address as "<email>" with subject "<incomplete-subject>"
    And I attach a valid image file as "<path>"
    Then the file named "<arialabel>" is uploaded
    When I send the email
    Then the message with subject "<incomplete-subject>" with the image is sent
    Examples:
      | email   | path | arialabel | incomplete-subject |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Spicy.PNG | Attachment: Spicy.PNG. Press enter to view the attachment and delete to remove it | Spicy_Valid |
      | dreamteamlite2@gmail.com | C:\Users\Evan\Downloads\Ran.jpg |   Attachment: Ran.jpg. Press enter to view the attachment and delete to remove it | Ran_Valid |
      | dreamteamlite3@gmail.com | C:\Users\Evan\Downloads\KidA.png |  Attachment: KidA.png. Press enter to view the attachment and delete to remove it | KidA_Valid |
      | evanbruchet@gmail.com | C:\Users\Evan\Downloads\MBDTF.jpg | Attachment: MBDTF.jpg. Press enter to view the attachment and delete to remove it | MBDTF_Valid |
      | evanbruchet2@gmail.com | C:\Users\Evan\Downloads\KSG.png |   Attachment: KSG.png. Press enter to view the attachment and delete to remove it | KSG_Valid |


  Scenario Outline: I try to send an email to a valid email address with an attached image greater than 25 MB
    When I compose an email
    And I specify a valid email address as "<email>" with subject "<incomplete-subject>"
    And I attach a valid image file of size greater than 25MB as "<path>"
    Then the file is uploaded to Google Drive with name "<filename>" and with a shareable link
    When I send the email
    And I approve access permissions for the file
    Then the message with subject "<incomplete-subject>" with the image is sent
    Examples:
      | email  | path | filename | incomplete-subject |
      | ebbruchet@gmail.com | C:\Users\Evan\Downloads\Earth.jpg | Earth.jpg | Earth |
      | dreamteamlite2@gmail.com | C:\Users\Evan\Downloads\Oil.jpg | Oil.jpg | Oil |
      | dreamteamlite3@gmail.com | C:\Users\Evan\Downloads\LosAngeles.jpg | LosAngeles.jpg | LosAngeles |
      | evanbruchet@gmail.com | C:\Users\Evan\Downloads\Germany.jpg | Germany.jpg | Germany |
      | evanbruchet2@gmail.com | C:\Users\Evan\Downloads\Mining.jpg | Mining.jpg | Mining |

  Scenario Outline: I try to send an email to a valid email address with an attached image using another button
    When I compose an email
    And I specify a valid email address as "<email>" with subject "<incomplete-subject>"
    And I attach a valid image file as "<path>" by inserting a photo
    When I send the email
    Then the message with subject "<incomplete-subject>" with the image is sent
    Examples:
      | email   | path | incomplete-subject |
      | dreamteamlite@gmail.com | C:\Users\Evan\Downloads\Spicy.PNG | Spicy_DiffButton |
      | dreamteamlite2@gmail.com | C:\Users\Evan\Downloads\Ran.jpg | Ran_DiffButton |
      | dreamteamlite3@gmail.com | C:\Users\Evan\Downloads\KidA.png | KidA_DiffButton |
      | evanbruchet@gmail.com | C:\Users\Evan\Downloads\MBDTF.jpg | MBDTF_DiffButton |
      | evanbruchet2@gmail.com | C:\Users\Evan\Downloads\KSG.png | KSG_DiffButton |


  Scenario Outline: I try to send an email to an invalid email address with an attached image
    When I compose an email
    And I specify an invalid email address as "<email>"
    And I attach a valid image file as "<path>"
    When I send the email
    Then I should see an error message
    Examples:
      | email   | path | 
      | asdf | C:\Users\Evan\Downloads\Spicy.PNG |
      | fsda | C:\Users\Evan\Downloads\Ran.jpg | 
      | yeet | C:\Users\Evan\Downloads\KidA.png |  
      | yata | C:\Users\Evan\Downloads\MBDTF.jpg | 
      | spaget | C:\Users\Evan\Downloads\KSG.png | 
