package by.epam.halavin.maintask.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Ehor Halavin
 * @version 1.0
 */
public class Car implements Serializable {
    private static final long serialVersionUID = 11;
    private int id;
    /**
     * Car's number
     */
    private String number = "";
    /**
     * Car's brand name
     */
    private String name = "";
    /**
     * Car's checkup end date
     */
    private Date date;

    /**
     * Function to get field value {@link Car#date}
     *
     * @return - checkup end date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Date determination {@link Car#date}
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Function to get field value {@link Car#id}
     *
     * @return - car id
     */
    public int getId() {
        return id;
    }

    /**
     * Id determination {@link Car#id}
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Function to get field value {@link Car#number}
     *
     * @return - car number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Number determination {@link Car#number}
     *
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Function to get field value {@link Car#name}
     *
     * @return - car name
     */
    public String getName() {
        return name;
    }

    /**
     * Name determination {@link Car#name}
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
                Car car = (Car) o;
                if (id == car.id &&
                        Objects.equals(date, car.date) &&
                        number.equals(car.number) &&
                        name.equals(car.name)) {
                    bool = true;
                }
            }
        }

        return bool;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, date);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
