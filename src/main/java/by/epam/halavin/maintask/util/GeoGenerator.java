package by.epam.halavin.maintask.util;

import by.epam.halavin.maintask.bean.geocoding.Point;
import by.epam.halavin.maintask.bean.user.Driver;
import by.epam.halavin.maintask.dao.repository.DriverRepository;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GeoGenerator extends Thread {
    public static final double maxLatitude = 53.969541;
    public static final double minLatitude = 53.832991;
    public static final double maxLongitude = 27.684456;
    public static final double minLongitude = 27.409485;
    public static final double difLatitude = maxLatitude - minLatitude;
    public static final double difLongitude = maxLongitude - minLongitude;


    @Override
    public void run() {
        Map<Integer, Driver> map = DriverRepository.getInstance().getFreeDrivers();
        Set<Map.Entry<Integer, Driver>> set = map.entrySet();
        Iterator<Map.Entry<Integer, Driver>> iterator = set.iterator();
        Driver driver;

        while (iterator.hasNext()) {
            driver = iterator.next().getValue();
            driver.setCurrentPoint(generatePoint());
        }
    }

    public Point generatePoint() {
        Point point = new Point();

        double lat = minLatitude + Math.random() * difLatitude;
        double longi = minLongitude + Math.random() * difLongitude;
        point.setLatitude(lat);
        point.setLongitude(longi);

        return point;
    }

    public void generatePointsFor(Collection<Driver> drivers) {
        Iterator<Driver> iterator = drivers.iterator();
        Driver driver;

        while (iterator.hasNext()) {
            driver = iterator.next();
            driver.setCurrentPoint(generatePoint());
        }
    }
}
