package by.epam.halavin.maintask.util;

import by.epam.halavin.maintask.bean.geocoding.Point;
import by.epam.halavin.maintask.bean.user.Driver;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeoGeneratorTest {

    @Test
    public void generatePoint() {
        Point[] points = new Point[10];
        GeoGenerator geoGenerator = new GeoGenerator();
        for (int i = 0; i < points.length; i++) {
            points[i] = geoGenerator.generatePoint();
        }

    }

    @Test
    public void generatePointsFor() {
        GeoGenerator geoGenerator = new GeoGenerator();
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver());
        drivers.add(new Driver());
        drivers.add(new Driver());
        drivers.add(new Driver());
        drivers.add(new Driver());
        drivers.add(new Driver());
        drivers.add(new Driver());

        geoGenerator.generatePointsFor(drivers);
        System.out.println(Objects.toString(drivers));
    }
}