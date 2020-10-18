package assertions;

import io.restassured.response.Response;

import static api.ScenarioContext.ContextEnum.HTTP_RESPONSE;
import static api.ScenarioContext.getContext;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class AssertionsAPI {

    public static void checkResponseStatusCode(int statusCode) {
        Response rawResponse = getContext(HTTP_RESPONSE);
        rawResponse.then().assertThat().statusCode(statusCode);
    }

    public static void checkResponseContains(String jsonFieldName, String expectedValue) {
        Response rawResponse = getContext(HTTP_RESPONSE);
        rawResponse.then().assertThat().body(jsonFieldName, equalTo(expectedValue));
    }

    public static void checkJsonSchema(String path){
        Response rawResponse = getContext(HTTP_RESPONSE);
        rawResponse.then().assertThat()
                .body(matchesJsonSchemaInClasspath(path));
    }

    public static void checkResponseContainsField(String fieldName){
        Response rawResponse = getContext(HTTP_RESPONSE);
        rawResponse.then().assertThat().body(containsString(fieldName));
    }

    public static void checkResponseBodyEqualsJsonFile(String path){
        Response rawResponse = getContext(HTTP_RESPONSE);
//        Json body = rawResponse.getBody();
    }
}
