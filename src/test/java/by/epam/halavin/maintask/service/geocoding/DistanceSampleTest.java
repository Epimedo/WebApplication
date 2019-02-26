package by.epam.halavin.maintask.service.geocoding;

import by.epam.halavin.maintask.bean.geocoding.Point;
import by.epam.halavin.maintask.service.geocoding.impl.DistanceSample;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DistanceSampleTest {

    @Test
    public void getDistance() throws JSONException, IOException {
        Point point1 = new Point(53.9141547, 27.5792643);
        Point point2 = new Point(55.73588480000001, 37.5174924);
        DistanceSample sample = new DistanceSample();
        double expected = 667.3725796044092;

        Assert.assertEquals(expected, sample.getDistance(point1, point2), 8);
    }
}