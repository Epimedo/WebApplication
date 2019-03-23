package by.epam.halavin.maintask.bean.geocoding;

import java.io.Serializable;
import java.util.Objects;

/**
 * Geographic point with its coordinates
 *
 * @author Ehor Halavin
 * @version 1.0
 */
public class Point implements Serializable {
    private static final long serialVersionUID = 14;
    private double latitude;
    private double longitude;

    /**
     * Default constructor
     */
    public Point() {
    }

    /**
     * Constructor - creates object with parameters
     *
     * @param latitude
     * @param longitude
     */
    public Point(final double latitude, final double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    /**
     * Function to get field value{@link Point#latitude}
     *
     * @return - point's latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Latitude determination {@link Point#latitude}
     *
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Function to get field value {@link Point#longitude}
     *
     * @return - point's longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Longitude determination {@link Point#longitude}
     *
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        boolean bool = false;

        if (this == o) {
            bool = true;
        } else {
            if (o == null || getClass() != o.getClass()) {
                bool = false;
            } else {
                Point point = (Point) o;
                if (Double.compare(point.latitude, latitude) == 0 &&
                        Double.compare(point.longitude, longitude) == 0) {
                    bool = true;
                }
            }
        }

        return bool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}
