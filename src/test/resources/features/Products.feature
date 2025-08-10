Feature: Products

  Background:
    Given the app is launched
    And the user is on the catalog

    @Products @Positive @Regression
    Scenario Outline: As the user, I can buy items "<conditions>" first
      Given the user is "<conditions>"
      When the user adds an item to the cart
      And proceeds to checkout "<conditions>"
      Then the purchase should be successful

      Examples:
      | conditions    |
      | with login    |
      | without login |

    @Products @Positive @Negative @Regression
    Scenario Outline: As the user, I "<verbs>" go to the next steps after fill the shipping address with "<conditions>"
      Given the user is "with login"
      When the user adds an item to the cart
      And the user fill the shipping address with "<conditions>"
      Then the user should received results conditions in checkout page based on "<conditions>" input
      Examples:
      |verbs   | conditions           |
      | can    | mandatory field only |
      | can    | all field            |
      | cannot | optional field only  |