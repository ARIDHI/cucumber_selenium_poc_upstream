Feature: Contact Functionality 
  Background:
    Given I am on the contact page

  Scenario: Successful get data from end point API
    Given I send a request to end point
    When  I get response
    Then  I should have response status 200
    Then  I should have response body

  Scenario Outline: Successful create a contact data
    Given I have entered all valid data
    When I click on the submit button
    Then I should see a validation message indicating "<success_message>"
    Examples:
      | success_message          |  |  |
      | Le message a été envoyé. |  |  |

  Scenario Outline: Forget the user genre data
    Given I have forget the user genre data
    When I click on the submit button
    Then I should see an alert worning indicating "<alert_message>"
    Examples:
      | alert_message                                   |  |  |
      | Veuillez sélectionner un élément dans la liste. |  |  |

  Scenario Outline: Forget required user data
    Given I have forget required user data
    When I click on the submit button
    Then I should see an error message indicating "<error_message>"
    Examples:
      | error_message                                  |  |  |
      | Veuillez remplir tous les champs obligatoires. |  |  |

  Scenario Outline: Forget required message data
    Given I have forget required user data
    When I click on the submit button
    Then I should see an alert worning indicating "<Alert>"
    Examples:
      | Alert                     |  |  |
      | Veuillez remlir ce champ. |  |  |

  Scenario Outline: Forget required company,mobile and title
    Given I have entered data exclude company, mobile and title
    When I click on the submit button
    Then I should see a validation message indicating "<success_message>"
    Examples:
      | success_message          |  |  |
      | Le message a été envoyé. |  |  |

  Scenario Outline: retry user validation after error process
    Given I have forget required user data
    When I click on the submit button
    And I add the forgotten user data
    And  I click on the submit button
    Then I should see a validation message indicating "<success_message>"

    Examples:
      | success_message          |  |  |
      | Le message a été envoyé. |  |  |
