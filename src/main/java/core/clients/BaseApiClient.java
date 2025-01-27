package core.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static core.configs.ConfigurationManager.config;

public class BaseApiClient {

    public BaseApiClient() {
        RestAssured.baseURI = config().apiBaseUrl();
        RestAssured.requestSpecification = RestAssured.given()
                .filter(new io.qameta.allure.restassured.AllureRestAssured())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        RestAssured.responseSpecification = RestAssured.expect()
                .header("Content-Type", "application/json")
                .header("charset", "UTF-8");
    }

    public Response sendGet(String endpoint) {
        return RestAssured.get(endpoint).then().extract().response();
    }
}
