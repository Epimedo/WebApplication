package by.epam.halavin.maintask.service.geocoding.impl;

import by.epam.halavin.maintask.bean.geocoding.Point;
import by.epam.halavin.maintask.service.geocoding.Sample;
import com.google.common.collect.Maps;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import static java.lang.Math.*;

public class DistanceSample extends Sample {
    private static final double EARTH_RADIUS = 6371.;

    /**
     * Геокодирует адрес
     *
     * @param address
     * @return
     * @throws IOException
     * @throws JSONException
     */
    private static Point getPoint(final String address) throws IOException, JSONException {
        final String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyBQ3loSSzKf2Fwh-3uztO1FDSwelHYRrgQ";
        final Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("address", address);
        final String url = baseUrl + '&' + encodeParams(params);
        final JSONObject response = JsonReader.read(url);
        JSONObject location = response.getJSONArray("results").getJSONObject(0);
        location = location.getJSONObject("geometry");
        location = location.getJSONObject("location");
        final double lng = location.getDouble("lng");
        final double lat = location.getDouble("lat");
        final Point point = new Point(lng, lat);

        return point;
    }

    /**
     * Преобразует значение из градусов в радианы
     *
     * @param degree
     * @return
     */
    private static double deg2rad(final double degree) {
        return degree * (Math.PI / 180);
    }

    public double getDistance(Point point1, Point point2) throws IOException, JSONException {
        final double dlng = deg2rad(point1.getLongitude() - point2.getLongitude());
        final double dlat = deg2rad(point1.getLatitude() - point2.getLatitude());

        final double a = sin(dlat / 2) * sin(dlat / 2) + cos(deg2rad(point2.getLatitude()))
                * cos(deg2rad(point1.getLatitude())) * sin(dlng / 2) * sin(dlng / 2);
        final double c = 2 * atan2(sqrt(a), sqrt(1 - a));

        return c * EARTH_RADIUS;
    }

}
