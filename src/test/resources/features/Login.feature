Feature: Login

  Background:
    Given the app is launched
    And the user is on the catalog
    And navigate to login page

    @Login @Positive @Regression
    Scenario: As the user, I can login with correct credentials
      When the user login with correct credentials
      Then the user successfully login should see the Logout menu in the navigation bar

    @Login @Negative @Regression
    Scenario Outline: As the user, I cannot login without "<conditions>"
      When the user login with "<conditions>"
      Then the user should see the error in login page

    Examples:
    |conditions                 |
    | blank credentials         |
    | blank username            |
    | blank password            |
    | random credentials        |
