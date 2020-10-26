package api;

import com.github.viclovsky.swagger.coverage.SwaggerCoverageRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static api.ScenarioContext.ContextEnum.HTTP_REQUEST_SPECIFICATION;
import static api.ScenarioContext.ContextEnum.HTTP_RESPONSE;
import static api.ScenarioContext.getContext;
import static api.ScenarioContext.setContext;
import static helpers.FileHelper.readFileAsString;
import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.http.ContentType.*;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class RestAssuredMain {

    public static void getRequest(@NonNull String endPoint) {
        RequestSpecification reqSpec = getContext(HTTP_REQUEST_SPECIFICATION);
        Response rawResponse = given().filter(new SwaggerCoverageRestAssured())
                .spec(reqSpec)
                .when()
                .get(endPoint);
        log.info("Response body if GET request is: " + rawResponse.body().prettyPrint());
        setContext(HTTP_RESPONSE, rawResponse);
    }

    public static void postRequest(@NonNull String endPoint, @NonNull String request) {
        RequestSpecification reqSpec = getContext(HTTP_REQUEST_SPECIFICATION);
        Response rawResponse = given().filter(new SwaggerCoverageRestAssured())
                .spec(reqSpec)
                .body(new File(request).exists() ? readFileAsString(request) : request)
                .when()
                .post(endPoint);
        log.info("Response body of POST request is: " + rawResponse.body().prettyPrint());
        setContext(HTTP_RESPONSE, rawResponse);
    }

    public static void putRequest(@NonNull String endPoint, @NonNull String request) {
        RequestSpecification reqSpec = getContext(HTTP_REQUEST_SPECIFICATION);
        Response rawResponse = given().filter(new SwaggerCoverageRestAssured())
                .spec(reqSpec)
                .body(new File(request).exists() ? new File(request) : request)
                .when()
                .put(endPoint);
        log.info("Response body of PUT request is: " + rawResponse.body().prettyPrint());
        setContext(HTTP_RESPONSE, rawResponse);
    }

    public static void deleteRequest(@NonNull String endPoint) {
        RequestSpecification reqSpec = getContext(HTTP_REQUEST_SPECIFICATION);
        Response rawResponse = given().filter(new SwaggerCoverageRestAssured())
                .spec(reqSpec)
                .when()
                .delete(endPoint);
        log.info("Response body of DELETE request is: " + rawResponse.body().prettyPrint());
        setContext(HTTP_RESPONSE, rawResponse);
    }

    public static void setupRequestSpecification(@NonNull String url, String contentType) {
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(url)
                .setConfig(config().encoderConfig(encoderConfig().defaultContentCharset(UTF_8)))
                .setContentType(getContentTypeByString(contentType))
                .setUrlEncodingEnabled(false)
                .setAccept(getContentTypeByString(contentType))
                .log(LogDetail.ALL)
                .build();
        setContext(HTTP_REQUEST_SPECIFICATION, reqSpec);
    }

    private static ContentType getContentTypeByString(String contentType) {
        ContentType type = null;
        switch (contentType) {
            case "json":
                type = JSON;
                break;
            case "xml":
                type = XML;
                break;
            case "text":
                type = TEXT;
                break;
            case "html":
                type = HTML;
                break;
            default:
                log.info("Unknown content type!");
        }
        return type;
    }

    private ResponseSpecification setupResponseSpecification(String contentType) {
        return new ResponseSpecBuilder()
                .expectContentType(getContentTypeByString(contentType))
                .log(LogDetail.ALL)
                .build();
    }
}
