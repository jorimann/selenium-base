package tests.api;

import business.api.services.ZipService;
import business.api.models.CityZips;
import business.api.models.Place;
import business.api.models.ZipInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZipServiceTests {
    private final ZipService zipSteps = new ZipService();

    @Test
    @DisplayName("Verify service returns expected ZIP codes for Stuttgart Degerloch")
    public void shouldReturnValidZipForStuttgartDegerloch() throws IOException {
        String COUNTRY_ABBREVIATION = "de";
        String STATE = "bw";
        String CITY = "Stuttgart";
        String EXPECTED_COUNTRY = "Germany";
        String EXPECTED_STATE = "Baden-Württemberg";
        String EXPECTED_PLACE = "Stuttgart Degerloch";
        String EXPECTED_ZIP_1 = "70597";
        String EXPECTED_ZIP_2 = "70567";

        CityZips returnedCityZips = zipSteps.getPostalCodesOfCity(COUNTRY_ABBREVIATION, STATE, CITY);

        assertEquals(EXPECTED_COUNTRY, returnedCityZips.getCountry());
        assertEquals(EXPECTED_STATE, returnedCityZips.getState());

        List<Place> places = filterPlacesByExpectedPlace(returnedCityZips.getPlaces(), EXPECTED_PLACE);

        //There are 2 Zip codes for Stuttgart-Degerloch
        assertEquals(2, places.size(), "Response contains unexpected count of places for specified place");
        assertEquals(1, filterPlacesByPostCode(places, EXPECTED_ZIP_1).size(), "Response returns unexpected zip code");
        assertEquals(1, filterPlacesByPostCode(places, EXPECTED_ZIP_2).size(), "Response returns unexpected zip code");
    }

    @ParameterizedTest()
    @DisplayName("Validate place names by country and postal code")
    @CsvFileSource(resources = "/api_countries.csv", numLinesToSkip = 1)
    public void shouldReturnValidPlaceNameByCountyAndPostalCode(String country, String zip, String expectedPlace) throws IOException {
        ZipInfo zipInfo = zipSteps.getPlaceByPostalCode(country, zip);

        assertEquals(1, zipInfo.getPlaces().size(), "Response returns unexpected count of places");
        assertEquals(expectedPlace, zipInfo.getPlaces().get(0).getPlaceName(), "Response returns unexpected name of Place");
    }

    private List<Place> filterPlacesByPostCode(List<Place> places, String postCode) {
        return places.stream().filter(place -> place.getPostCode().equals(postCode)).toList();
    }

    private List<Place> filterPlacesByExpectedPlace(List<Place> places, String postCode) {
        return places.stream().filter(place -> place.getPlaceName().equals(postCode)).toList();
    }
}
