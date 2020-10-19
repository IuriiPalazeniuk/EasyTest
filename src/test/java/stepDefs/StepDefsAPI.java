package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static api.RestAssuredMain.*;
import static assertions.AssertionsAPI.*;

@Slf4j
public class StepDefsAPI {


    /**
     * Verifying status code.
     *
     * @param statusCode - expected statusCode
     *
     * @example: Then Status Code is 201
     */
    @Then("Status Code is {int}")
    public void statusCodeIs(int statusCode) {
        checkResponseStatusCode(statusCode);
    }

    /**
     * Request sending.
     *
     * @param requestMethod - GET or DELETE method
     * @param url - endpoint
     *
     * @example: When User sends 'GET' request to "/get?foo1=bar1&foo2=bar2"
     */
    @When("User sends {string} request to {string}")
    public void userSendsRequestToURL(String requestMethod, String url) {
        switch (requestMethod) {
            case "GET":
                getRequest(url);
                break;
            case "DElETE":
                deleteRequest(url);
                break;
            default:
                log.info("Please provide correct request.");
        }
    }

    /**
     * Request specification.
     *
     * @param contentType - request content type
     * @param url - base url
     *
     * @example: Given User sets 'google.com' link and 'json' contentType for request
     */
    @Given("User sets {string} link and {string} contentType for request")
    public void userSetsURLAndContentTypeForRequest(String url, String contentType) {
        setupRequestSpecification(url, contentType);
    }

    /**
     * Request sending.
     *
     * @param requestMethod - POST or PUT method
     * @param url - endpoint
     *
     * @example: When User sends 'POST' request to "/get?foo1=bar1&foo2=bar2" with body '{\"title\": \"Test Post Request with Body passed as string\"}'
     */
    @When("User sends {string} request to {string} with body {string}")
    public void userSendsRequestToURLWithBody(String requestMethod, String url, String body) {
        switch (requestMethod) {
            case "POST":
                postRequest(url, body);
                break;
            case "PUT":
                putRequest(url, body);
                break;
            default:
                log.info("Please provide correct request.");
        }
    }

    /**
     * Verifying json schema.
     *
     * @param path - absolut path to the json schema file
     *
     * @example: Then Response body matches "get_schema.json" schema
     */
    @Then("Response body matches {string} schema")
    public void responseBodyMatchesSchema(String path) {
        checkJsonSchema(path);
    }

    /**
     * Verifying that response body contains filed.
     *
     * @param fieldName
     *
     * @example: Then Response body contains 'price' filed
     */
    @Then("Response body contains {string} filed")
    public void responseBodyContainsPriceFiled(String fieldName) {
        checkResponseContainsField(fieldName);
    }

    /**
     * Verifying that json response is equals to expected json file.
     *
     * @param filePath - absolut path to the json schema file
     *
     * @example: Then Response body is equal to "get_query_response_data.json"
     */
    @Then("Response body is equal to {string}")
    public void responseBodyIsEqualToGet_response_dataJson(String filePath) {
        checkResponseBodyEqualsJsonFile(filePath);
    }

    /**
     * Verifying that json response contains field with value.
     *
     * @param field
     * @param value
     *
     * @example: Then Response body contains 'price' filed with '35' value
     */
    @Then("Response body contains {string} filed with {string} value")
    public void responseBodyContainsFieldFiledWithValueValue(String field, String value) {
        checkResponseContains(field, value);
    }
}
