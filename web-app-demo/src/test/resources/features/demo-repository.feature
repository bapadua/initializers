Feature: Manipulate database

  Scenario: Should persist an entity
    Given the following payload exists:
      | code   | 123codex |
      | name   | testName |
      | number | 123      |
    When the payload is saved
    Then the object can be found at the database