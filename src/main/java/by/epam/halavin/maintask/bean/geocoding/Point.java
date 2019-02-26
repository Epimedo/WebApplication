package by.epam.halavin.maintask.bean.geocoding;

import java.util.Objects;

public class Point {
    private double latitude;
    private double longitude;

    public Point() {
    }

    public Point(final double latitude, final double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

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
