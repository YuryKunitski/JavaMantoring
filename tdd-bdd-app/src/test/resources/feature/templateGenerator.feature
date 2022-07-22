Feature: template generator operations

  Scenario: successful generating message
    Given client provide variable 'value' with value 'clientValue'
    When run generator
    Then the generated message is 'Some text: clientValue'

  Scenario: unsuccessful generating message
    Given client provide variable 'value' with value ''
    When run generator and throw IllegalArgumentException
    Then exception message is 'Value is not provided.'