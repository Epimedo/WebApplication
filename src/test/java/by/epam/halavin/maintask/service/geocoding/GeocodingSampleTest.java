package by.epam.halavin.maintask.service.geocoding;

import by.epam.halavin.maintask.service.geocoding.impl.GeocodingSample;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

public class GeocodingSampleTest {

    @Test
    public void getPoint() throws IOException, JSONException {
        GeocodingSample geocodingSample = new GeocodingSample();
        System.out.println(geocodingSample.getPoint("улица Жуковского, 29"));
    }
}