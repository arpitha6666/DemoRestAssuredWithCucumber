Feature: Validate Add Place APIs

  @AddPlace @Regression
  Scenario Outline: Verify if Place is being added successfully using AddPlaceApi
    Given Add place payload with "<name>", "<language>" and "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then verify the status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify placeid created maps to "<name>" using "GetPlaceAPI"

    Examples:
      | name    | language | address                  |
      | AAhouse | English  | 1150, world cross center |
      | Iris    | French   | 7853, Iris avenue        |

  @DeletePlace @Regression
  Scenario:
     Given Deleteplace Payload
     When user calls "DeletePlaceAPI" with "DELETE" http request
     Then verify the status code 200
     And "status" in response body is "OK"


