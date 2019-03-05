package by.epam.halavin.maintask.service.geocoding.impl;


import by.epam.halavin.maintask.bean.geocoding.Point;
import by.epam.halavin.maintask.service.geocoding.Sample;
import com.google.common.collect.Maps;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class GeocodingSample extends Sample {
    public Point getPoint(String address) throws IOException, JSONException {
        final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyBQ3loSSzKf2Fwh-3uztO1FDSwelHYRrgQ";
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("address", "Беларусь, Минск, " + address);
        final String url = baseUrl + '&' + encodeParams(params);
        final JSONObject response = JsonReader.read(url);

        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");

        final double lng = location.getDouble("lng");
        final double lat = location.getDouble("lat");

        return new Point(lat, lng);
    }
}
