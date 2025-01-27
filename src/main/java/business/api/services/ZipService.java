package business.api.services;

import business.api.models.CityZips;
import business.api.models.ZipInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.clients.BaseApiClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipService {
    private final BaseApiClient apiClient = new BaseApiClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final int MAX_RESPONSE_TIME_IN_MS = 1000;

    @Step
    public ZipInfo getPlaceByPostalCode(String countryAbbreviation, String postalCode) throws JsonProcessingException {

        Response response = apiClient.sendGet(String.format("%s/%s", countryAbbreviation, postalCode));

        assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Unexpected status code");
        assertTrue(response.getTime() < MAX_RESPONSE_TIME_IN_MS, "Response time exceeded the maximum limit");

        assertEquals("application/json", response.getContentType());
        assertEquals("UTF-8", response.getHeader("charset"));
        return objectMapper.readValue(response.getBody().asString(), ZipInfo.class);
    }

    @Step
    public CityZips getPostalCodesOfCity(String countryAbbreviation, String state, String city) throws JsonProcessingException {
        Response response = apiClient.sendGet(String.format("%s/%s/%s", countryAbbreviation, state, city));
        assertEquals(HttpStatus.SC_OK, response.getStatusCode(), "Unexpected status code");
        assertTrue(response.getTime() < MAX_RESPONSE_TIME_IN_MS, "Response time exceeded the maximum limit");
        return objectMapper.readValue(response.getBody().asString(), CityZips.class);
    }
}
