package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static api.RestAssuredMain.*;
import static assertions.AssertionsAPI.*;

@Slf4j
public class StepDefsAPI {

    @Then("Status Code is {int}")
    public void statusCodeIs(int statusCode) {
        checkResponseStatusCode(statusCode);
    }

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

    @Given("User sets {string} link and {string} contentType for request")
    public void userSetsURLAndContentTypeForRequest(String url, String contentType) {
        setupRequestSpecification(url, contentType);
    }

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

    @Then("Response body matches {string} schema")
    public void responseBodyMatchesSchema(String path) {
        checkJsonSchema(path);
    }

    @Then("Response body contains {string} filed")
    public void responseBodyContainsPriceFiled(String fieldName) {
        checkResponseContainsField(fieldName);
    }


    @Then("Response body is equal to {string}")
    public void responseBodyIsEqualToGet_response_dataJson(String filePath) {
        checkResponseBodyEqualsJsonFile(filePath);
    }

    @Then("Response body contains {string} filed with {string} value")
    public void responseBodyContainsFieldFiledWithValueValue(String field, String value) {
        checkResponseContains(field, value);
    }
}
