@smoke
Feature: Verify framework API test steps

  Scenario: Verify GET request
    Given User sets 'google.com' link and 'json' contentType for request
    When User sends 'GET' request to '?foo1=bar1&foo2=bar2'
    When User sends 'POST' request to 'sdfg!.df' with body '{\"title\": \"Test Post Request with Body passed as string\"}'
    Then Status Code is 201
    And Response body matches "get_schema.json" schema
    And Response body contains 'price' filed
    And Response body is equal to 'get_response_data.json'
    And Response body contains 'field' filed with 'value' value

  Scenario: Verify GET request with query in the request URI
    When User sends 'GET' request to "{BASE_API_URL}/get?foo1=bar1&foo2=bar2"
    Then Status Code is 200
    And Response body matches "get_schema.json" schema
    And Response body is equal to "get_query_response_data.json"

  Scenario: Verify GET request with query as param
    When User sends "GET" request to "{BASE_API_URL}/get" with query "?foo1=bar1&foo2=bar2"
    Then Status Code is 200
    And Response body matches "get_query_schema.json" schema
    And Response body is equal to "get_query_response_data.json"

  Scenario: Verify POST request with Body
    When User sends 'POST' request to '{BASE_API_URL}/post' with body 'post_body.json'
    Then Status Code is 200
    And Response body matches "post_body_schema.json" schema
    And Response body contains 'field' filed with 'value' value
    And Response body is equal to "post_body_response_data.json"

  Scenario: Verify POST request with Form Data
    When User sends "POST" request to "{BASE_API_URL}/post" with Form Data "post_formData.js"
    Then Status Code is "200"
    And Response body matches "post_formData_schema.json" schema
    Then Response "body.files" contains:
      | mercerLogo.png |
    And Response "property" "content" in "body.form" is "equal to" "\"{\"title\":\"Test Post Request with Form Data\",\"description\":\"Test Description\",\"date\":\"2020-03-03T00:00:00.000Z\"}\""
    And Response "property" "action" in "body.form" is "equal to" "Submit"

  Scenario: Verify POST request with Body passed as string
    When User sends "POST" request to "{BASE_API_URL}/post" with Body "{\"title\": \"Test Post Request with Body passed as string\"}"
    Then Status Code is "200"

  Scenario: Verify POST request with Body passed as uniqueMap variable
    When User sends "POST" request to "{BASE_API_URL}/post" with Body "[{\"title\": \"Test Post Request with Body passed as uniqueMap variable\"}]"
    Then Status Code is "200"
    And User remembers response property "data" in "body" as "requestData"
    When User sends "POST" request to "{BASE_API_URL}/post" with Body "$requestData"
    Then Status Code is "200"

  Scenario: Verify POST request with Body passed as Doc String
    When User sends "POST" request to "{BASE_API_URL}/post" with Body:
      """
      {
        "title": "Test Post Request with Body passed as string"
      }
      """
    Then Status Code is 200

  Scenario: Verify POST request with custom Content-Type and Body passed as Doc String
    When User sends "POST" request to "{BASE_API_URL}/post" with Content-Type "application/json" and Body:
      """
      {
        "title": "Test Post Request with Body passed as string"
      }
      """
    Then Status Code is 200

  Scenario: Verify POST request with Body and replaced values
    When User sends "GET" request to "{BASE_API_URL}/get"
    Then User remembers response property "url" in "body" as "desc"
    When User sends "POST" request to "{BASE_API_URL}/post" with Body "post_body.json" and replaced values:
      | title       | title_repalced |
      | description | $desc          |
    Then Response "property" "title" in "body.data" is "equal to" "title_repalced"
    And Response "property" "description" in "body.data" is "equal to" "https://postman-echo.com/get"

  Scenario: Verify POST request with Form Data and replaced values
    When User sends "GET" request to "{BASE_API_URL}/get"
    Then User remembers response property "url" in "body" as "desc"
    When User sends "POST" request to "{BASE_API_URL}/post" with Form Data "post_formData.js" and replaced values:
      | action     | action_replaced |
      | content    | $desc           |
      | attachment | attach_replaced |
    Then Response "property" "content" in "body.form" is "equal to" "https://postman-echo.com/get"
    And Response "property" "action" in "body.form" is "equal to" "action_replaced"
    And Response "property" "attachment" in "body.form" is "equal to" "attach_replaced"

  Scenario: Verify POST request with Body and replaced values using jsonPath
    When User sends "POST" request to "{BASE_API_URL}/post" with Body "post_body_test_jsonPath.json" and replaced values:
      | $.store.book[*].title | Replaced Title |
    Then Response "every property" "title" in "body.data.store.book" is "equal to" "Replaced Title"

  Scenario: Verify POST request with Body and replaced values using jsonPath
    When User sends "POST" request to "{BASE_API_URL}/post" with Body "post_body_test_replace_values.json" and replaced values:
      | string  | Replaced String          |
      | number  | 4321                     |
      | obj     | {"replaced": "Replaced"} |
      | arr     | [4,3,2,1]                |
      | boolean | false                    |
      | null    | null                     |
    Then Response "property" "string" in "body.data" is a "String"
    And Response "property" "string" in "body.data" is "equal to" "Replaced String"
    And Response "property" "number" in "body.data" is a "Number"
    And Response "property" "number" in "body.data" is "equal to" "4321"
    And Response "property" "obj" in "body.data" is an "Object"
    And Response "property" "obj" in "body.data" is "equal to" "{\"replaced\": \"Replaced\"}"
    And Response "property" "arr" in "body.data" is an "Array"
    And Response "property" "arr" in "body.data" is "equal to" "[4,3,2,1]"
    And Response "property" "boolean" in "body.data" is a "Boolean"
    And Response "property" "boolean" in "body.data" is "equal to" "false"
    And Response "property" "null" in "body.data" is a "Null"
    And Response "property" "null" in "body.data" is "equal to" "null"

  Scenario: Verify response using jsonPath verification steps
    When User sends "POST" request to "{BASE_API_URL}/post" with Body "post_body_test_jsonPath.json"
    Then User verifies response contains property "title" with value "Sayings of the Century"
    And User verifies response contains property "title" in "body.data.store.book" with value "Sayings of the Century"
    And User verifies response contains property "author.id" in "body.data.store.book" with value "1"
    And User verifies response doesn't contain property "title" with value "Test doesn\'t contain"
    And User verifies response doesn't contain property "title" in "body.data.store.book" with value "Test doesn\'t contain"
    And User verifies response doesn't contain property "author.id" in "body.data.store.book" with value "10"
    And User verifies response with jsonPath "$..body.data.store.bicycle.color" result is equal to "red"
    And User verifies response with jsonPath "$..body.data.store.book[?(@.price==12.99)].title" result is equal to "Sword of Honour"
    And User verifies response with jsonPath "$..body.data.store.book[?(@.price<20)].title" result contains "Moby Dick"
    And User verifies response with jsonPath "$..body.data.store.book[?(@.price<20)].title" result is equal to "[\"Sayings of the Century\",\"Sword of Honour\",\"Moby Dick\"]"
    And User verifies response with jsonPath "$..body.data.store.book[?(@.price==12.99)]" result is not equal to "[]"
    And User verifies response with jsonPath "$..body.data.store.book[?(@.price<20)].title" result doesn't contain "Non existing title"