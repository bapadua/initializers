Feature: Controller tests

  Scenario: Should save an entity
    Given the following payload exists:
      | code   | 123codex |
      | name   | testName |
      | number | 123      |
    When the user send a new post request
    Then the server responds with '201' status